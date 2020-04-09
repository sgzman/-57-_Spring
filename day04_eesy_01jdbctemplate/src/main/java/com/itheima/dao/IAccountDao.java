package com.itheima.dao;

import com.itheima.domain.Account;

/**
 * 账户的持久层接口
 * @author sugz
 */
public interface IAccountDao {

    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account finAccountById(Integer accountId);

    /**
     * 根据名称查询账户
     * @param name
     * @return
     */
    Account findAccountByName(String name);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
