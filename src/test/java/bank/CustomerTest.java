package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer cust;

    @BeforeEach
    void setup() {
        cust = new Customer(1, 123, "Kwan");
        cust.setAccountManager(new StubAccountManager());
    }

    @Test
    void testCreateCustomerNegativeIdNegativePinEmptyName() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(-1, -1, ""));
    }

    @Test
    void testCreateCustomerNegativeIdPositivePinSpaceOnlyName() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(-1, 1, "   "));
    }

    @Test
    void testCreateCustomerNegativeIdNegativePinValidName() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(-1, -1, "Noi"));
    }

    @Test
    void testCreateCustomerPositiveIdPositivePinEmptyName() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(1, 1, ""));
    }

    @Test
    void testCreateCustomerPositiveIdNegativePinSpaceOnlyName() {
        assertThrows(IllegalArgumentException.class,
                ()-> new Customer(1, -1, "   "));
    }

    @Test
    void testCreateCustomerPositiveIdPositivePinValidName() {
        Customer cust = new Customer(1, 1, "Noi");
        assertEquals("Noi", cust.getName());
    }

    @Test
    void testGetId() {
        assertEquals(1, cust.getId());
    }

    @Test
    void testGetName() {
        assertEquals("Kwan", cust.getName());
    }

    @Test
    void testSetValidName() {
        cust.setName("Noon");
        assertEquals("Noon", cust.getName());
    }

    @Test
    void testSetSpaceOnlyName() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> cust.setName("   "));
        assertEquals("Customer name must not be empty", exception.getMessage());
    }

    @Test
    void testSetEmptyName() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> cust.setName(""));
        assertEquals("Customer name must not be empty", exception.getMessage());
    }

    @Test
    void testPinMatch() {
        assertTrue(cust.match(123));
    }

    @Test
    void testPinMatchButNegative() {
        assertFalse(cust.match(-123));
    }

    @Test
    void testPinNotMatch() {
        assertFalse(cust.match(999));
    }

    @Test
    void testGetAccount() {
        BankAccount account = cust.getAccount();
        assertEquals(500, account.getBalance());
    }
}