package com.almundo.example.callcenter;

import com.almundo.example.callcenter.entities.Call;
import com.almundo.example.callcenter.services.Dispatcher;
import com.almundo.example.callcenter.services.OperatorService;
import com.almundo.example.callcenter.utils.CallProperties;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterApplicationTests {

	@Autowired
	private OperatorService service;

	@Autowired
	private CallProperties properties;

	@Autowired
	private Dispatcher dispatcher;

	@Test
	public void assertThatTenCallsAreTakenByOperators() {
		Boolean[] responses = new Boolean[10];
		Boolean[] expectedResponses = new Boolean[10];
		Arrays.fill(expectedResponses, true);

		Thread[] calls = new Thread[10];
		for(int i = 0 ; i < calls.length; i++){
			int callNumber = i;
			calls[i] = new Thread(() ->
					responses[callNumber] = dispatcher.dispatchCall(new Call("Call "+callNumber, properties))
			);
			calls[i].start();
		}

		for (Thread call : calls) {
			try {
				call.join();
			} catch (InterruptedException e) {
				Assert.fail();
			}
		}

		Assert.assertArrayEquals(expectedResponses, responses);
	}

	@Test
	public void assertThatTwentyCallsAreTakenByOperatorsAnd() {
		Boolean[] responses = new Boolean[20];
		Boolean[] expectedResponses = new Boolean[20];
		Arrays.fill(expectedResponses, true);

		Thread[] calls = new Thread[20];
		for(int i = 0 ; i < calls.length; i++){
			int callNumber = i;
			calls[i] = new Thread(() ->
					responses[callNumber] = dispatcher.dispatchCall(new Call("Call "+callNumber, properties))
			);
			calls[i].start();
		}

		for (Thread call : calls) {
			try {
				call.join();
			} catch (InterruptedException e) {
				Assert.fail();
			}
		}

		Assert.assertArrayEquals(expectedResponses, responses);
	}

	@Test
	public void assertThatThirtyCallsAreTakenByAllEmployeeTypes() {
		Boolean[] responses = new Boolean[30];
		Boolean[] expectedResponses = new Boolean[30];
		Arrays.fill(expectedResponses, true);

		Thread[] calls = new Thread[30];
		for(int i = 0 ; i < calls.length; i++){
			int callNumber = i;
			calls[i] = new Thread(() ->
					responses[callNumber] = dispatcher.dispatchCall(new Call("Call "+callNumber, properties))
			);
			calls[i].start();
		}

		for (Thread call : calls) {
			try {
				call.join();
			} catch (InterruptedException e) {
				Assert.fail();
			}
		}

		Assert.assertArrayEquals(expectedResponses, responses);
	}

	@Test
	public void assertThatFortyCallsAreNotTakenUsingOperatorService() {
		Boolean[] responses = new Boolean[40];
		Boolean[] expectedResponses = new Boolean[40];
		Arrays.fill(expectedResponses, true);

		Thread[] calls = new Thread[40];
		for(int i = 0 ; i < calls.length; i++){
			int callNumber = i;
			calls[i] = new Thread(() ->
					responses[callNumber] = service.dispatchCall(new Call("Call "+callNumber, properties))
			);
			calls[i].start();
		}

		for (Thread call : calls) {
			try {
				call.join();
			} catch (InterruptedException e) {
				Assert.fail();
			}
		}

		Assert.assertThat(responses, IsNot.not(IsEqual.equalTo(expectedResponses)));
	}

	@Test
	public void assertThatFortyCallsAreTakenUsingDispatcherService() {
		Boolean[] responses = new Boolean[40];
		Boolean[] expectedResponses = new Boolean[40];
		Arrays.fill(expectedResponses, true);

		Thread[] calls = new Thread[40];
		for(int i = 0 ; i < calls.length; i++){
			int callNumber = i;
			calls[i] = new Thread(() -> {
				responses[callNumber] = dispatcher.dispatchCall(new Call("Call " + callNumber, properties));
			}
			);
			calls[i].start();
		}

		for (Thread call : calls) {
			try {
				call.join();
			} catch (InterruptedException e) {
				Assert.fail();
			}
		}

		Assert.assertArrayEquals(expectedResponses, responses);
	}

}
