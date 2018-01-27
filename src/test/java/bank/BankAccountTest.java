package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setup() {
        account = new BankAccount(1000);
    }

    @Test
    void deposit() {
        account.deposit(200);
        assertEquals(1200, account.getBalance());
    }

    @Test
    void depositNegativeAmount() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(-200));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }
    @Test
    void withdraw() {
        account.withdraw(200);
        assertEquals(800, account.getBalance());
    }

    @Test
    void withdrawNegativeAmount() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(-200));
        assertEquals("Amount must be greater than zero", exception.getMessage());
    }
    @Test
    void withdrawMoreThanBalance() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(1200));
        assertEquals("Can't withdraw more than balance", exception.getMessage());
    }
}