package com.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 12:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;
    private String name;

    //
    private List<Blog> blogs = new ArrayList<>();
}
