package com.library.test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import com.library.Library;
import com.library.Pass;
import com.library.lending.LendingInformation;
import com.library.lending.LendingObject;
import com.library.object.Book;
import com.library.object.CD;
import com.library.object.Movie;
import com.library.object.MovieGenre;
import com.library.object.MovieType;
import com.library.object.ObjectInformation;
import com.library.object.Title;
import com.library.people.Artist;
import com.library.people.Customer;
import com.library.people.CustomerStatus;
import com.library.people.Employee;
import com.library.people.Sexuality;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'unrea' at '28.10.16 14:46' with Gradle 3.0
 *
 * @author unrea, @date 28.10.16 14:46
 */
public class LibraryTest {
	//	private static final String PERSISTENCE_UNIT_NAME = "derby-embedded-inmemory";

	//private static final String PERSISTENCE_UNIT_NAME = "derby-network";
	private static final String PERSISTENCE_UNIT_NAME = "h2-mem";

	private static EntityManagerFactory factory;

	final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LibraryTest.class);

	@org.junit.BeforeClass
	public static void setup() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		
	}

	@Test
	public void testCD(){
		
		CD cd = new CD();
		cd.setName("TestCD");
		int titlesCount = 13;
		Artist artist = new Artist();
		artist.setName("TestArtist");
		List<Title> titles = new ArrayList<>();
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			try{		
								
				for (int i = 0; i < titlesCount; i++) {
					Title title = new Title();
					title.setName("Title" + i);
					title.setDuration(123);		
					cd.addTitle(title);
					title.addArtist(artist);
					
					titles.add(title);
					
					entityManager.persist(title);
				}
				
				entityManager.persist(artist);
				entityManager.persist(cd);
				transaction.commit();
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}
			
			CD testCd = entityManager.find(CD.class, cd.getId());
			assertEquals(cd, testCd);
			assertEquals(titlesCount,testCd.getTitles().size());
			
		} finally{
			entityManager.close();
		}
		
	}
	

	@Test
	public void testMovie(){
		Movie movie = new Movie();
		movie.setType(MovieType.FULLHD);
		movie.setName("John Wick");
		movie.setDurationInMin(101);
		movie.setDisks(1);
		movie.setMovieGenre(MovieGenre.ACTION);
		EntityManager entityManager = factory.createEntityManager();
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(movie);
				transaction.commit();
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}
			Movie testMovie = entityManager.find(Movie.class, movie.getId());
			assertEquals(movie, testMovie);
			
			@SuppressWarnings("unchecked")
			List<ObjectInformation> movies = entityManager.createQuery(
					"SELECT c FROM OBJECT_INFORMATION c WHERE c.name LIKE :custName")
				    .setParameter("custName", "John Wick")
				    .setMaxResults(10)
				    .getResultList();
			
			for(ObjectInformation obj : movies){
				System.out.println(obj.getName());
			}
			//TODO: This is not a test - the output has to be validated or this part removed
			
		} finally{
			entityManager.close();
		}
	}
	
	@Test
	public void testCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("TestCustomer");
		customer.setSex(Sexuality.MALE);
		Pass pass = new Pass();
		customer.addPass(pass);
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				
				entityManager.persist(customer);
				entityManager.persist(pass);
				transaction.commit();
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}
			Customer testCustomer = entityManager.find(Customer.class, customer.getId());
			assertEquals(testCustomer.getFirstName(), testCustomer.getPass().getCustomer().getFirstName());
			assertEquals(customer, testCustomer);
				
		} finally{
			entityManager.close();
		}
		
	}
	
	@Test
	public void testLend() {
		Customer customer = new Customer();
		customer.setFirstName("TestCustomer");
		customer.setSex(Sexuality.FEMALE);
		customer.setStatus(CustomerStatus.ACTIVE);
		
		Pass pass = new Pass();
		pass.setValidDate(new GregorianCalendar(2017, 02, 10).getTime());	
		customer.addPass(pass);
		
		Book book = new Book();
		book.setName("TestBook");
		
		LendingInformation lendingInfo = new LendingInformation();
		customer.addLendingInformation(lendingInfo);
		LendingObject lendingObj = new LendingObject(book);
		lendingObj.addLendingInformation(lendingInfo);
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{				
				entityManager.persist(customer);
				entityManager.persist(pass);
				entityManager.persist(book);
				entityManager.persist(lendingInfo);
				entityManager.persist(lendingObj);
				transaction.commit();
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}
			Customer testCustomer = entityManager.find(Customer.class, customer.getId());
			Book testBook = entityManager.find(Book.class, book.getId());
			assertEquals(testCustomer.getLendingInfos().get(0).getLendingObject().getObjectInformation().getId(), testBook.getId());
			
				
		} finally{
			entityManager.close();
		}
		
	}
	
	@Test
	public void testPersistAndDeleteBook() {
		Book book = new Book();
		book.setName("TestBook");
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				
				entityManager.persist(book);
				
				Book testBook = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook);
				
				entityManager.remove(book);
				transaction.commit();
				
				Book testBookDel = entityManager.find(Book.class, book.getId());
				assertEquals(null, testBookDel);
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}		
	}
	
	@Test
	public void testDeleteLibrary(){
		
		Library lib = new Library();
		Book book = new Book();
		LendingObject lendingBook = new LendingObject(book);
		lib.addLendingObject(lendingBook);
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(lendingBook);
				entityManager.persist(book);
				
				entityManager.persist(lib);
				transaction.commit();
				transaction.begin();
				Book testBook = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook);
				LendingObject testInformation = entityManager.find(LendingObject.class, lendingBook.getId());
				assertEquals(lendingBook, testInformation);
				
				entityManager.remove(lib);
				transaction.commit();
				//Book informations should not be deleted
				Book testBook2 = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook2);
				
				//All physical objects should be deleted if a library gets deleted
				LendingObject lendingBook2 = entityManager.find(LendingObject.class, lendingBook.getId());
				assertEquals(null, lendingBook2);
				
				
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}	
		
	}
	
	
	
	
	@Test
	public void testDeleteBookWithoutDeletingLibrary(){
		Library lib = new Library();
		Book book = new Book();
		LendingObject lendingBook = new LendingObject(book);
		lib.addLendingObject(lendingBook);
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(lendingBook);
				entityManager.persist(book);
				entityManager.persist(lib);
				transaction.commit();
				transaction.begin();
				Book testBook = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook);
				LendingObject testInformation = entityManager.find(LendingObject.class, lendingBook.getId());
				assertEquals(lendingBook, testInformation);
				
				entityManager.remove(book);
				transaction.commit();
				//Library informations should not be deleted
				Library testLibrary = entityManager.find(Library.class, lib.getId());
				assertEquals(lib, testLibrary);
				
				//All physical objects should be deleted if a library gets deleted
				LendingObject lendingBook2 = entityManager.find(LendingObject.class, lendingBook.getId());
				assertEquals(null, lendingBook2);
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}	
		
	}
	
	
	@Test
	public void testDeleteCustomer(){
		Pass pass = new Pass();
		Customer cust = new Customer();
		cust.addPass(pass);
		LendingInformation info = new LendingInformation();
		cust.addLendingInformation(info);
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(cust);
				entityManager.persist(info);
				
				transaction.commit();
				transaction.begin();
				Customer testCust = entityManager.find(Customer.class, cust.getId());
				assertEquals(cust, testCust);
				
				LendingInformation testInformation = entityManager.find(LendingInformation.class, info.getId());
				assertEquals(info, testInformation);
				
				entityManager.remove(cust);
				transaction.commit();
				//Lending informations should not be deleted
				LendingInformation testInfo = entityManager.find(LendingInformation.class, info.getId());
				assertEquals(info, testInfo);
				
				Customer testCust2 = entityManager.find(Customer.class, cust.getId());
				assertEquals(null, testCust2);
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}	
		
	}
	
	@Test
	public void testDeleteEmployee(){
		Employee emp = new Employee();
		emp.setFirstName("TestEmployee");
		Library lib = new Library();
		lib.addEmployee(emp);
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(lib);				
				
				transaction.commit();
				transaction.begin();
				Employee testEmp = entityManager.find(Employee.class, emp.getId());
				assertEquals(emp, testEmp);
				
				Library testLibrary = entityManager.find(Library.class, lib.getId());
				assertEquals(lib, testLibrary);
				
				//Remove employee - he should be deleted
				emp.getLibrary().removeEmployee(emp);
				//entityManager.remove(testEmp);
				transaction.commit();
				transaction.begin();
				//Library should not be deleted
				Library testLib2 = entityManager.find(Library.class, lib.getId());
				assertEquals(lib, testLib2);
				
				//Employee should be deleted
				Employee testEmp2 = entityManager.find(Employee.class, testEmp.getId());
				assertEquals(null, testEmp2);
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}	
		
	}
	
	@Test
	public void testDeleteLendingInformation(){
		Library lib = new Library();
		Employee emp = new Employee();
		lib.addEmployee(emp);
		Customer cust = new Customer();
		Pass pass = new Pass();
		cust.addPass(pass);
		Book book = new Book();
		LendingObject lenobj = new LendingObject(book);
		LendingInformation leninfo = new LendingInformation();
		lenobj.addLendingInformation(leninfo);
		leninfo.setEmployee(emp);
		
		
		EntityManager entityManager = factory.createEntityManager();
		
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				entityManager.persist(lenobj);
				entityManager.persist(leninfo);
				
				transaction.commit();
				transaction.begin();
				
				Employee testEmp = entityManager.find(Employee.class, emp.getId());
				assertEquals(emp, testEmp);
				
				Book testBook = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook);
				
				Pass testPass = entityManager.find(Pass.class, pass.getId());
				assertEquals(pass, testPass);
				
				Customer testCust = entityManager.find(Customer.class, cust.getId());
				assertEquals(cust, testCust);
				
				LendingObject testLenobj = entityManager.find(LendingObject.class, lenobj.getId());
				assertEquals(lenobj, testLenobj);
				
				LendingInformation testInformation = entityManager.find(LendingInformation.class, leninfo.getId());
				assertEquals(leninfo, testInformation);
				
				
				
				entityManager.remove(leninfo);
				transaction.commit();
					
				//Nothing except LendingInformation should be deleted
				Employee testEmp2 = entityManager.find(Employee.class, emp.getId());
				assertEquals(emp, testEmp2);
				
				Book testBook2 = entityManager.find(Book.class, book.getId());
				assertEquals(book, testBook2);
				
				Pass testPass2 = entityManager.find(Pass.class, pass.getId());
				assertEquals(pass, testPass2);
				
				Customer testCust2 = entityManager.find(Customer.class, cust.getId());
				assertEquals(cust, testCust2);
				
				LendingObject testLenobj2 = entityManager.find(LendingObject.class, lenobj.getId());
				assertEquals(lenobj, testLenobj2);
				
				LendingInformation testInformation2 = entityManager.find(LendingInformation.class, leninfo.getId());
				assertEquals(null, testInformation2);
				
			} finally{
				if(transaction.isActive()) transaction.rollback();
			}		
				
		} finally{
			entityManager.close();
		}	
		
	}
}
