package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;
public class AccountService {
    AccountDAO accountDAO;
    public AccountService()
    {
        this.accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO)
    {
        this.accountDAO = accountDAO;
    }
    public List<Account> getallAccounts(){
        return accountDAO.getAllAccounts();
    }
    public Account addAccount(Account account)
    {
        if(account.getUsername().isBlank())
        {
            return null;
        }
        if(account.getPassword().length() < 4)
        {
            return null;
        }
        List<Account> accounts = this.getallAccounts();
        if(accounts.contains(account))
        {
            return null;
        }
        Account result = new Account();
        result = accountDAO.addAccount(account);
        return result;
    }
    public Account findAccount(Account account)
    {
        Account result = new Account();
        result = accountDAO.findAccount(account);
        return result;
    }
}
