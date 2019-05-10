package br.com.bookstore.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;

import br.com.bookstore.model.Payment;
import br.com.bookstore.model.Shopping;
import br.com.bookstore.model.dto.AuditoryDTO;
import br.com.bookstore.model.dto.AuthenticationDTO;
import br.com.bookstore.model.dto.CreditCardDTO;
import br.com.bookstore.model.dto.CustomerDTO;
import br.com.bookstore.model.dto.PaymentDTO;
import br.com.bookstore.repository.PaymentRepository;
import br.com.bookstore.service.exception.ObjectNotFoundException;

@Service
public class PaymentService {

	private final PaymentRepository paymentRepository;
	private final ShoppingService shoppingService;
	private final DiscoveryClient discoveryClient;
	private final RestTemplate restTemplate;

	@Autowired
	public PaymentService(PaymentRepository paymentRepository, ShoppingService shoppingService,
			DiscoveryClient discoveryClient, RestTemplate restTemplate) {
		this.paymentRepository = paymentRepository;
		this.discoveryClient = discoveryClient;
		this.shoppingService = shoppingService;
		this.restTemplate = restTemplate;
	}

	/**
	 * Find Payment By ID.
	 * 
	 * @param id
	 * @return
	 */
	public PaymentDTO findPayment(Integer id) {
		Optional<Payment> obj = paymentRepository.findById(id);
		return convertToDTO(fromOptional(id, obj));
	}

	/**
	 * Insert new Payment
	 * 
	 * @param paymentDTO
	 * @return
	 * @throws Exception
	 */
	public PaymentDTO insertPayment(PaymentDTO paymentDTO) throws Exception {
		Shopping shopping = shoppingService.find(paymentDTO.getRequestId());

		if (validatedPayment(shopping, paymentDTO)) {
			return new PaymentDTO(paymentRepository.save(new Payment("PAYED", shopping)));
		} else {
			return new PaymentDTO(paymentRepository.save(new Payment("NOT PAYED", shopping)));
		}

	}

	private boolean validatedPayment(Shopping shopping, PaymentDTO paymentDTO) throws Exception {
		CustomerDTO customerDTO = new CustomerDTO(shopping.getCustomer(), "pwd123");
		ServiceInstance instance = discoveryClient.getInstancesById("")
		AuthenticationDTO customer = 
				
				authenticateCustomer(customerDTO);
		AuthenticationDTO creditCard = authenticateCreditCard(paymentDTO.getCreditCardDTO());
		return (customer.getValidation() && creditCard.getValidation());
	}

	/**
	 * Update Payment status.
	 * 
	 * @param obj
	 * @return
	 */
	public PaymentDTO updatePaymentStatus(PaymentDTO updateObj) {
		Optional<Payment> obj = paymentRepository.findById(updateObj.getId());
		Payment payment = fromOptional(updateObj.getId(), obj);
		payment.setStatus(updateObj.getStatus());
		return new PaymentDTO(paymentRepository.save(payment));
	}

	/**
	 * Delete Payment by ID.
	 * 
	 * @param id
	 */
	public void deletePaymentById(Integer id) {
		paymentRepository.deleteById(id);
	}

	private PaymentDTO convertToDTO(Payment obj) {
		return new PaymentDTO(obj);
	}

	private Payment fromOptional(Integer id, Optional<Payment> obj) {
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Kind: " + Payment.class.getName()));
	}

	public AuthenticationDTO authenticateCustomer(CustomerDTO customerDTO) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		List<HttpMessageConverter<?>> messageConverterList = restTemplate.getMessageConverters();
		messageConverterList.add(jsonMessageConverter);
		restTemplate.setMessageConverters(messageConverterList);

		HttpEntity<CustomerDTO> entity = new HttpEntity<>(customerDTO, headers);

		ResponseEntity<AuthenticationDTO> response = restTemplate.exchange(getApiServerUrl(), HttpMethod.POST, entity,
				AuthenticationDTO.class);

		return response.getBody();
	}

	private URI getApiServerUrl() throws URISyntaxException {
		return new URI("http://localhost:8060/v1/customers");
	}

	public AuthenticationDTO authenticateCreditCard(CreditCardDTO creditCardDTO) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		List<HttpMessageConverter<?>> messageConverterList = restTemplate.getMessageConverters();
		messageConverterList.add(jsonMessageConverter);
		restTemplate.setMessageConverters(messageConverterList);

		HttpEntity<CreditCardDTO> entity = new HttpEntity<>(creditCardDTO, headers);

		ResponseEntity<AuthenticationDTO> response = restTemplate.exchange(getApiServerUrlCredit(), HttpMethod.POST,
				entity, AuthenticationDTO.class);

		return response.getBody();
	}

	private URI getApiServerUrlCredit() throws URISyntaxException {
		return new URI("http://localhost:8095/v1/credit/transactions");
	}

	public AuditoryDTO authenticatePayment(PaymentDTO paymentDTO) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
		List<HttpMessageConverter<?>> messageConverterList = restTemplate.getMessageConverters();
		messageConverterList.add(jsonMessageConverter);
		restTemplate.setMessageConverters(messageConverterList);

		HttpEntity<PaymentDTO> entity = new HttpEntity<>(paymentDTO, headers);

		ResponseEntity<AuditoryDTO> response = restTemplate.exchange(getApiServerUrlAuditory(), HttpMethod.POST, entity,
				AuditoryDTO.class);

		return response.getBody();
	}

	private URI getApiServerUrlAuditory() throws URISyntaxException {
		return new URI("http://localhost:8080/v1/auditory");
	}

}
