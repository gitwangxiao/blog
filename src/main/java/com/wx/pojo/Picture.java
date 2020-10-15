package com.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wx
 * @Description
 * @date 2020/8/13 15:21
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
