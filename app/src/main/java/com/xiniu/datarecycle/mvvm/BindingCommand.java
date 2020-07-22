package com.xiniu.datarecycle.mvvm;

/**
 * 创建者：wyz
 * 创建时间：2020-04-22
 * 功能描述：
 * 更新者：
 * 更新时间：
 * 更新描述：
 */
public class BindingCommand{
    BindingAction action;

    public BindingCommand(BindingAction action) {
        this.action = action;
    }

    public void execute() {
        if (action == null) {
            return;
        }
        action.call();
    }

}
