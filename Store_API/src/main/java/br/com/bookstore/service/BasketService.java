package br.com.bookstore.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bookstore.model.Basket;
import br.com.bookstore.model.Book;
import br.com.bookstore.model.dto.AuthenticationDTO;
import br.com.bookstore.model.dto.BasketDTO;
import br.com.bookstore.model.dto.CustomerDTO;
import br.com.bookstore.repository.BasketRepository;
import br.com.bookstore.repository.BookRepository;
import br.com.bookstore.service.exception.ObjectNotFoundException;

@Service
public class BasketService {

	private final BasketRepository basketRepository;
	private final BookRepository bookRepository;

	@Autowired
	public BasketService(BasketRepository basketRepository, BookRepository bookRepository) {
		this.bookRepository = bookRepository;
		this.basketRepository = basketRepository;
	}

	/**
	 * Find Basket by ID
	 * 
	 * @param id
	 * @return
	 */
	public BasketDTO findById(Integer id) {
		Optional<Basket> obj = basketRepository.findById(id);
		return convertToDTO(obj.orElseThrow(() -> new ObjectNotFoundException(getExceptionMessage(id))));
	}

	/**
	 * Find all Baskets stored.
	 * 
	 * @return
	 */
	public List<BasketDTO> findAllBaskets() {
		List<Basket> baskets = (List<Basket>) basketRepository.findAll();
		List<BasketDTO> basketsDTO = new ArrayList<>();
		for (Basket basket : baskets) {
			basketsDTO.add(convertToDTO(basket));
		}
		return basketsDTO;
	}

	/**
	 * 
	 * @param BasketDTO
	 * @return
	 * @throws Exception
	 */
	public Basket insertBasket(BasketDTO basketDTO) {
//		if (CustumerValidation(basketDTO)) {
		return basketRepository.save(convertToEntity(basketDTO));
//		} else {
//			throw new Exception();
//		}
	}

	/**
	 * Clean all books from the basket.
	 * 
	 * @param id
	 */
	public void cleanBasket(Integer id) {
		Optional<Basket> search = basketRepository.findById(id);
		Basket basket = search.orElseThrow(() -> new ObjectNotFoundException(getExceptionMessage(id)));
		basket.setBook(new ArrayList<Book>());
		basketRepository.save(basket);
	}

	/**
	 * Insert a book in a Basket
	 * 
	 * @param basketId
	 * @param bookId
	 * @return
	 */
	public BasketDTO insertBookBasket(Integer basketId, Integer bookId) {
		Basket basket = convertToEntity(findById(basketId));
		Optional<Book> obj = bookRepository.findById(bookId);
		Book book = obj.orElseThrow(() -> new ObjectNotFoundException(getExceptionMessage(bookId)));
		basket.addBook(book);
		return convertToDTO(basketRepository.save(basket));
	}

	/**
	 * Update Basket from a DTO
	 * 
	 * @param obj
	 * @return
	 */
	public Basket updateBasket(BasketDTO updateObj) {
		Basket basket = convertToEntity(findById(updateObj.getId()));
		update(basket, updateObj);
		return basketRepository.save(basket);
	}

	private void update(Basket basket, BasketDTO updateObj) {
		basket.setCustomer(updateObj.getCustomer());
		basket.setBook(updateObj.getBooks());
	}

	private String getExceptionMessage(Integer id) {
		return "Objeto not found! Id: " + id + ", Kind: " + Basket.class.getName();
	}

	public BasketDTO convertToDTO(Basket basket) {
		return new BasketDTO(basket, "*****");
	}

	public Basket convertToEntity(BasketDTO basketDTO) {
		Basket basket = new Basket(basketDTO.getCustomer(), basketDTO.getBooks());
		basket.setId(basketDTO.getId());
		return basket;
	}

	public AuthenticationDTO autenticarUsuario(CustomerDTO customerDTO) throws Exception {

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
		return new URI("http://localhost:8080/v1/customers");
	}
}
