package com.library.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import com.library.Library;
import com.library.lending.LendingInformation;
import com.library.lending.LendingObject;
import com.library.object.Book;
import com.library.object.CD;
import com.library.object.Magazine;
import com.library.object.Movie;
import com.library.object.ObjectInformation;
import com.library.people.Customer;
import com.library.people.Employee;
import com.library.people.Publisher;

public class LoadTest {
	private static final String PERSISTENCE_UNIT_NAME = "h2-mem";

	private static EntityManagerFactory factory;
	
	private static long libraryId = 0;

	final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LibraryTest.class);

	@org.junit.BeforeClass
	public static void setup() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		fillDummyEntries(5, 20, 800, 100, 5);
	}
	
	
	private static void fillDummyEntries(int nbEmployees, int nbCustomers, int nbLendingObjects, int nbObjectInformations, int nbPublishers) {
		EntityManager entityManager = factory.createEntityManager();
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
		Random random = new Random();
		Library library = new Library();
		entityManager.persist(library);
		library.setName("Root Library");
		
		List<Employee> employees = new ArrayList<Employee>();
		for(int i = 0; i < nbEmployees; i++) {
			Employee employee = new Employee();
			employee.setFirstName("Surname" + i);
			employee.setSecondName("Lastname" + i);
			employees.add(employee);
			library.addEmployee(employee);

			entityManager.persist(employee);
		}
		
		List<Customer> customers = new ArrayList<Customer>();
		for(int i = 0; i < nbCustomers; i++) {
			Customer customer = new Customer();
			customer.setFirstName("Surname" + i);
			customer.setSecondName("Lastname" + i);
			customers.add(customer);
			entityManager.persist(customer);
		}
		
		List<Publisher> publishers = new ArrayList<Publisher>();
		for(int i = 0; i < nbPublishers; i++) {
			Publisher publisher = new Publisher();
			publisher.setName("Publisher" + i);
			publishers.add(publisher);
			entityManager.persist(publisher);
		}
		
		List<ObjectInformation> objectInformations = new ArrayList<ObjectInformation>();
		for(int i = 0; i < nbObjectInformations; i++) {
			int mediaType = i % 4;
			
			ObjectInformation obj = null;
			switch(mediaType)
			{
			case 0:
				Book book = new Book();
				book.setIsbn("0000000-0000-" + i);
				book.setName("Book" + i);
				obj = book;
				break;
			case 1:
				Magazine magazine = new Magazine();
				magazine.setVersion(i);
				magazine.setName("Magazine" + i);
				obj = magazine;
				break;
			case 2:
				Movie movie = new Movie();
				movie.setDisks(random.nextInt(5));
				movie.setName("DVD" + i);
				obj = movie;
				break;
			case 3:
				CD disk = new CD();
				disk.setDisks(random.nextInt(5));
				disk.setName("CD" + i);
				obj = disk;
				break;
			}

			int rndPublishersNb = random.nextInt(5);
			
			for(int k = 0; k < rndPublishersNb; k++) {
				obj.addPublisher(publishers.get(random.nextInt(publishers.size())));
			}
			
			objectInformations.add(obj);
			entityManager.persist(obj);
		}
		
		
		List<LendingObject> lendingObjects = new ArrayList<LendingObject>();
		for(int i = 0; i < nbLendingObjects; i++) {
			LendingObject lendingObject = new LendingObject(objectInformations.get(random.nextInt(objectInformations.size())));
			
			int rndLendingsNb = random.nextInt(5);
			for(int k = 0; k < rndLendingsNb; k++) {
				LendingInformation lendingInformation = new LendingInformation();
				lendingInformation.setLendingObject(lendingObject);
				Customer customer = customers.get(random.nextInt(customers.size()));
				customer.addLendingInformation(lendingInformation);
				
				Employee employee = employees.get(random.nextInt(employees.size()));
				employee.addLendingInformation(lendingInformation);
				
				lendingObject.addLendingInformation(lendingInformation);
				entityManager.persist(lendingInformation);
			}
			
			library.addLendingObject(lendingObject);
			lendingObjects.add(lendingObject);
			entityManager.persist(lendingObject);
		}
		

				entityManager.persist(library);
				libraryId = library.getId();
				Library lib = entityManager.find(Library.class, libraryId);
				Assert.assertNotNull(lib);
				transaction.commit();
			}
			finally{
				if(transaction.isActive()) transaction.rollback();
			}		
		} finally{
			entityManager.close();
		}	
	}
	
	@Test
	public void test(){
		EntityManager entityManager = factory.createEntityManager();
		try{
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			try{
				System.out.println("Before find");
				Library library = entityManager.find(Library.class, libraryId);
				Assert.assertNotNull(library);
				System.out.println("Load employees-----------------------------------------------");
				System.out.println("Nb employees : " + library.getEmployees().size());
				System.out.println("Loaded employees-----------------------------------------------");

				transaction.commit();
				
			}
			finally{
				if(transaction.isActive()) transaction.rollback();
			}		
		} finally{
			entityManager.close();
		}	
	}
}
