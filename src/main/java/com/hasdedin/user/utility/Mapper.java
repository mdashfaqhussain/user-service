package com.hasdedin.user.utility;


import com.hasdedin.user.dto.RegisterUserdto;
import com.hasdedin.user.entity.BudgetUser;


public class Mapper {
	
	
	
	public  static BudgetUser convertToBudgetUser(RegisterUserdto registerDto) {
        BudgetUser budgetUser = new BudgetUser();
        budgetUser.setName(registerDto.getName());
        budgetUser.setEmail(registerDto.getEmail());
        budgetUser.setPassword(registerDto.getPassword());
        return budgetUser;
    }

}
