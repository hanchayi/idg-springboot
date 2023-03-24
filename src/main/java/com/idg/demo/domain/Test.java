package com.idg.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import lombok.Data;

// {"items":[{"a":[[{"b":1}]]}]}
@Data
class ComplexJson {
    List<ItemA> items;
}
@Data
class ItemA {
    List<List<ItemB>> a;
}

// {\"b\":1}
@Data
class ItemB implements Serializable{
    Integer b;
}

// https://github.com/alibaba/fastjson
public class Test {
    public static void main(String[] args) {
      // ItemB b = JSON.parseObject("{\"b\":1}", ItemB.class); //反序列化
      // String text1 = JSON.toJSONString(b); //序列化
      ComplexJson vo = JSON.parseObject("{\"items\":[{\"a\":[[{\"b\":1}]]}]}", new TypeReference<ComplexJson>(){}); //反序列化
      String text = JSON.toJSONString(vo); //序列化
      List<List<ItemB>> list2 = JSON.parseObject("[[{\"b\":1}]]", new TypeReference<List<List<ItemB>>>(){});
      List<ItemB> listb = list2.get(0);
      ItemB b = list2.get(0).get(0);
      // String text = JSON.toJSONString(list2); //序列化
      // assert(text == "{\"items\":[{\"a\":[[{\"b\":1}]]}]}");
      System.out.println(text);

    }
   
}