package com.library.dslTest;

import static de.dhbw.stuttgart.swe2.javadsl.FromServiceImpl.from;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.library.lending.LendingInformation;
import com.library.lending.LendingObject;
import com.library.lending.ObjectStatus;
import com.library.object.ObjectInformation;

import de.dhbw.stuttgart.swe2.javadsl.ToMany;


public class DslTest {

	private static final ToMany<ObjectInformation, LendingObject> OBJECT_INFORMATION_TO_LENDING_OBJECT = new ObjectInformationToLendingObject();
	private static final ToMany<LendingObject, LendingInformation> LENDING_OBJECT_TO_LENDING_INFORMATION = new LendingObjectToLendingInformation();

	private static class ObjectInformationToLendingObject implements ToMany<ObjectInformation, LendingObject>{

		@Override
		public List<LendingObject> get(ObjectInformation input) {
			// TODO Auto-generated method stub
			return input.getLendingObjects();
		}
		
	}
	
	private static class LendingObjectToLendingInformation implements ToMany<LendingObject, LendingInformation>{

		@Override
		public List<LendingInformation> get(LendingObject input) {
			// TODO Auto-generated method stub
			return input.getLendingInfo();
		}	
	}

	private static ToMany<ObjectInformation, LendingObject> lendingObjects() {
		return OBJECT_INFORMATION_TO_LENDING_OBJECT;
	}
	
	private static ToMany<LendingObject, LendingInformation> lendingInformations() {
		return LENDING_OBJECT_TO_LENDING_INFORMATION;
	}
	
	
	@Test
	public void test() throws Exception {
		LendingInformation info = new LendingInformation();
		info.setStatus(ObjectStatus.NEW);
		ArrayList<LendingInformation> lendingInfoList = new ArrayList<LendingInformation>();
		lendingInfoList.add(info);
		ArrayList<LendingObject> lendingObjectList = new ArrayList<LendingObject>();
		lendingObjectList.add(new LendingObject());
		lendingObjectList.get(0).setLendingInfo(lendingInfoList);
		input.setLendingObjects(lendingObjectList);
		List<LendingInformation> lendingInfos = from(ObjectInformation.class).join(lendingObjects()).join(lendingInformations()).get(input);
		Assert.assertEquals(1, lendingInfos.size());
		Assert.assertEquals(ObjectStatus.NEW, lendingInfos.get(0).getStatus());
	}
	
	ObjectInformation input = new ObjectInformation(){
		
	};
}
