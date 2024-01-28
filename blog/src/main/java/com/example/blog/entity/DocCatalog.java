package com.example.blog.entity;

import com.example.blog.util.Contents;
import lombok.Data;

import java.util.List;

/**
 * @author: 308501
 * @time: 2021/11/15 14:02
 * @description :文档列表
 */

@Data
public class DocCatalog implements Comparable<DocCatalog>{
    public static String sort = "desc";
    private int value;
    private String title;
    private int parent;
    private List<DocCatalog> children;


    @Override
    public int compareTo(DocCatalog o) {
        if (sort.equals("desc")){
            return o.value-this.value;
        }
        return this.value-o.value;
        //如何重写方法
        //按年龄升序排序比较
        //this(当前调用方法的对象)   o(参数传入)
        //所有比较最底层的逻辑都是发生两两比较逻辑的,返回比较结果
        //只关心结果结果三种:
        //正数:   this.age - o.age    >
        //负数:   this.age - o.age    <
        //0       this   ==

//        return this.age-o.age; 升序排序
//        return o.age-this.age; 降序排序
    }
}
