
class Bank {
    private final long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }

    // Check whether an account number exists.
    private boolean isValidAccount(int account) {
        return account >= 1 && account <= balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        // Both accounts must exist.
        if (!isValidAccount(account1) || !isValidAccount(account2)) {
            return false;
        }

        // The source account must have enough money.
        if (balance[account1 - 1] < money) {
            return false;
        }

        // Update both balances only after validation.
        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;

        return true;
    }

    public boolean deposit(int account, long money) {
        if (!isValidAccount(account)) {
            return false;
        }

        balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (!isValidAccount(account)) {
            return false;
        }

        if (balance[account - 1] < money) {
            return false;
        }

        balance[account - 1] -= money;
        return true;
    }
}
