package com.kubaokleja.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import com.kubaokleja.order.Order;
import com.kubaokleja.order.OrderStatus;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
	
	@InjectMocks
	private CartService cartService;
	@Mock
	private CartHandler cartHandler;
	@Captor
	private ArgumentCaptor<Cart> argumentCaptor;
	
	@Test
	void processCartShouldSendToPrepare() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		cart.addOrderToCart(order);

		given(cartHandler.canHandleCart(cart)).willReturn(true);
		
		//when
		Cart resultCart = cartService.processCart(cart);
		
		//then
		verify(cartHandler).sendToPrepare(cart);
		then(cartHandler).should().sendToPrepare(cart);
		
        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
		
	}
	
	@Test
	void processCartShouldSendToPrepareWithArgumentCaptor() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		cart.addOrderToCart(order);
				
		given(cartHandler.canHandleCart(cart)).willReturn(true);
		
		//when
		Cart resultCart = cartService.processCart(cart);
		
		//then
		then(cartHandler).should().sendToPrepare(argumentCaptor.capture());
		
		assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
		
	}
	
	@Test
	void processCartShouldNotSendToPrepareWithArgumentMatchers() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		cart.addOrderToCart(order);
		
		given(cartHandler.canHandleCart(any())).willReturn(false);
		
		//when
		Cart resultCart = cartService.processCart(cart);
		
		//then
		verify(cartHandler, never()).sendToPrepare(cart);
		then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
		
	}
    @Test
    void canHandleCartShouldReturnMultipleValues() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        //then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));

    }
    
    @Test
    void canHandleCartShouldThrowException() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        
        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));

    }
	@Test
	void shouldDoNothingWhenProcessCart() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		cart.addOrderToCart(order);
		
		given(cartHandler.canHandleCart(cart)).willReturn(true);
		
		willDoNothing().given(cartHandler).sendToPrepare(cart);
	//	willDoNothing().willThrow(IllegalStateException.class).given(cartHandler);
		//when
		Cart resultCart = cartService.processCart(cart);
		
		//then
		then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
		
	}
	
	@Test
	void shouldAnswerWhenProcessCart() {
		//given
		Order order = new Order();
		Cart cart = new Cart();
		cart.addOrderToCart(order);
		
		doAnswer(invocationOnMock ->{
			Cart argumentCart = invocationOnMock.getArgument(0);
			argumentCart.clearCart();
			return true;
		}).when(cartHandler).canHandleCart(cart);
		
		//when
		Cart resultCart = cartService.processCart(cart);
		
		//then
		then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders().size(), equalTo(0));
		
	}
	
	@Test
	void deliveryShouldBeFree() {
		//given
		Cart cart = new Cart();
		cart.addOrderToCart(new Order());
		cart.addOrderToCart(new Order());
		cart.addOrderToCart(new Order());
		
		given(cartHandler.isDeliveryFree(cart)).willCallRealMethod();
		//when
		boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);
		
		//then 
		assertTrue(isDeliveryFree);
	}
	
}
