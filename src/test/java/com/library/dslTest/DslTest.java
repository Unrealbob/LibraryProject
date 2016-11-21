package com.library.dslTest;

import static de.dhbw.stuttgart.swe2.javadsl.FromServiceImpl.from;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.library.Library;
import com.library.lending.LendingInformation;
import com.library.lending.LendingObject;
import com.library.lending.ObjectStatus;
import com.library.object.Book;
import com.library.object.ObjectInformation;
import com.library.people.Employee;

import de.dhbw.stuttgart.swe2.javadsl.AbstractToOne;
import de.dhbw.stuttgart.swe2.javadsl.Filter;
import de.dhbw.stuttgart.swe2.javadsl.FromService;
import de.dhbw.stuttgart.swe2.javadsl.ToMany;
import de.dhbw.stuttgart.swe2.javadsl.ToOne;


public class DslTest {

	private static final ToMany<ObjectInformation, LendingObject> OBJECT_INFORMATION_TO_LENDING_OBJECT = new ObjectInformationToLendingObject();
	private static final ToMany<LendingObject, LendingInformation> LENDING_OBJECT_TO_LENDING_INFORMATION = new LendingObjectToLendingInformation();
	private static final ToOne<LendingInformation, LendingObject> LENDING_INFORMATION_TO_LENDING_OBJECT = new LendingInformationToLendingObject();
	private static final ToOne<LendingObject, ObjectInformation> LENDING_OBJECT_TO_OBJECT_INFORMATION = new LendingObjectToObjectInformation();
	private static final ToMany<Library, Employee> LIBRARY_TO_EMPLOYEE = new LibraryToEmployee();
	private static final ToMany<Employee, LendingInformation> EMPLOYEE_TO_LENDING_INFORMATION = new EmployeeToLendingInformation();

	private ToMany<Library, Employee> libraryToEmployee() {
		// TODO Auto-generated method stub
		return LIBRARY_TO_EMPLOYEE;
	}
	
	private ToMany<Employee, LendingInformation> employeeToLendingInfo() {
		// TODO Auto-generated method stub
		return EMPLOYEE_TO_LENDING_INFORMATION;
	}
	
	private static ToMany<ObjectInformation, LendingObject> lendingObjects() {
		return OBJECT_INFORMATION_TO_LENDING_OBJECT;
	}
	
	private static ToMany<LendingObject, LendingInformation> lendingInformations() {
		return LENDING_OBJECT_TO_LENDING_INFORMATION;
	}
	
	private static ToOne<LendingObject, ObjectInformation> objectInformation() {
		return LENDING_OBJECT_TO_OBJECT_INFORMATION;
	}
	
	private static ToOne<LendingInformation, LendingObject> lendingObject() {
		return LENDING_INFORMATION_TO_LENDING_OBJECT;
	}
	
	private static Filter<Employee> FilteredLibraryToEmployee(String string) {
		// TODO Auto-generated method stub
		return new FilteredLibraryToEmployee(string);
	}
	
	private static Filter<LendingInformation> FilteredEmployeeToLendingInfo(Date lendingDate) {
		// TODO Auto-generated method stub
		return new FilteredEmpToLendingInfo(lendingDate);
	}
	
	private static class FilteredEmpToLendingInfo implements Filter<LendingInformation>{

		private Date date = new Date();
		public FilteredEmpToLendingInfo(Date date){
			this.date= date;
		}
		@Override
		public boolean get(LendingInformation output) {
			// TODO Auto-generated method stub
			if(output.getLendingDate().equals(date))
				return true;
			return false;
		}

	}
	
	private static class FilteredLibraryToEmployee implements Filter<Employee>{

		private String text;
		
		public FilteredLibraryToEmployee(String text){
			this.text=text;
		}
		
		@Override
		public boolean get(Employee output) {
			// TODO Auto-generated method stub
			if(output.getFirstName().contains(text))
				return true;
			return false;
		}

	}
	
	private static class LendingObjectToLendingInformation implements ToMany<LendingObject, LendingInformation>{

		@Override
		public List<LendingInformation> get(LendingObject input) {
			// TODO Auto-generated method stub
			return input.getLendingInfo();
		}	
	}
	private static class LendingInformationToLendingObject extends AbstractToOne<LendingInformation, LendingObject> {

		@Override
		public LendingObject getOne(LendingInformation input) {
			// TODO Auto-generated method stub
			return input.getLendingObject();
		}
	}

	
	private static class LendingObjectToObjectInformation extends AbstractToOne<LendingObject, ObjectInformation> {

		@Override
		public ObjectInformation getOne(LendingObject input) {
			// TODO Auto-generated method stub
			return input.getObjectInformation();
		}

		
	}
	private static class LibraryToEmployee implements ToMany<Library, Employee> {

		@Override
		public List<Employee> get(Library input) {
			// TODO Auto-generated method stub
			return input.getEmployees();
		}
	}
	
	private static class EmployeeToLendingInformation implements ToMany<Employee, LendingInformation>{

		@Override
		public List<LendingInformation> get(Employee input) {
			// TODO Auto-generated method stub
			return input.getLendingInfos();
		}
	}
	
	private static class ObjectInformationToLendingObject implements ToMany<ObjectInformation, LendingObject>{

		@Override
		public List<LendingObject> get(ObjectInformation input) {
			// TODO Auto-generated method stub
			return input.getLendingObjects();
		}
		
	}
	
	
	@Test
	public void testObjectInformationToManyLendingInformations() throws Exception {
		ObjectInformation input = new ObjectInformation() {
		};

		LendingObject lendingObjectList = new LendingObject(input);
		input.getLendingObjects().add(lendingObjectList);
		LendingInformation info = new LendingInformation();
		info.setStatus(ObjectStatus.NEW);
		lendingObjectList.getLendingInfo().add(info);
		
		
		List<LendingInformation> lendingInfos = from(ObjectInformation.class).join(lendingObjects()).join(lendingInformations()).get(input);
		Assert.assertEquals(1, lendingInfos.size());
		Assert.assertEquals(ObjectStatus.NEW, lendingInfos.get(0).getStatus());		
	}
	
	@Test
	public void testFilterOnFirstName(){
		
		Library library = new Library();
		Employee hans = new Employee();
		hans.setFirstName("Hans");
		hans.setSecondName("Zimmer");
		Employee hans2 = new Employee();
		hans2.setFirstName("Klaus");
		hans2.setSecondName("Mustermann");
		library.getEmployees().add(hans);
		library.getEmployees().add(hans2);
		
		List<Employee> employeeLib = from(Library.class).join(libraryToEmployee()).filter(FilteredLibraryToEmployee("s")).get(library);
		
		boolean testBool = false;
		if(employeeLib.contains(hans) && employeeLib.contains(hans2))
			testBool = true;
		Assert.assertEquals(true, testBool);
		
	}
	@Test
	public void testFilterOnDate(){
		Employee dieter = new Employee();
		LendingInformation info = new LendingInformation();
		Date lendingDate = new Date();
		dieter.setFirstName("Hans");
		dieter.setSecondName("Zimmermann");
		info.setLendingDate(lendingDate);
		info.setEmployee(dieter);
		
		List<LendingInformation> lendingInfo = from(Employee.class).join(employeeToLendingInfo()).filter(FilteredEmployeeToLendingInfo(lendingDate)).get(dieter);
		
		for(LendingInformation emp : lendingInfo){
			Assert.assertEquals(lendingDate, emp.getLendingDate());
		}
		
	}
	

	@Test
	public void testLendingInformationToOneObject() throws Exception {
		LendingInformation inputLending = new LendingInformation();
		ObjectInformation objInfo = new ObjectInformation() {
		};
		LendingObject lendingObject = new LendingObject(objInfo);
		inputLending.setLendingObject(lendingObject);
		inputLending.getLendingObject().getObjectInformation().setName("Illumniati");
		
	
		FromService<LendingInformation> from = from(LendingInformation.class);
		ObjectInformation objectInfo = from.join(lendingObject()).join(objectInformation()).getOne(inputLending);
		System.out.println(objectInfo.getName());
		Assert.assertEquals("Illumniati", objectInfo.getName());
	}
	
	
	

}
