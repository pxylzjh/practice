package com.pxy.lambda;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author puxy
 * @version 1.0
 * @description lambda表达式常用示例-构造方法的引用
 * @date 2021/1/17 20:47
 */
public class TestLambda4 extends FunctionInterface {

    public static void main(String[] args) {
        /**
         * 一般需要声明接口,该接口作为对象生成器,通过 类名::new 的方式生成对象
         */
        //自己重写方法体
        ItemBlankConstruct itemBlankConstruct1 = () -> new Item();
        Item item1 = itemBlankConstruct1.getItem();

        //引用构造方法
        ItemBlankConstruct itemBlankConstruct2 = Item::new;
        Item item2 = itemBlankConstruct2.getItem();

        ItemParamConstruct itemParamConstruct = Item::new;
        Item item3 = itemParamConstruct.getItem("xx", 12);
    }

}

interface ItemBlankConstruct {
    Item getItem();
}

interface ItemParamConstruct {
    Item getItem(String name, Integer age);
}

@AllArgsConstructor
@NoArgsConstructor
class Item {
    private String name;
    private Integer age;
}
