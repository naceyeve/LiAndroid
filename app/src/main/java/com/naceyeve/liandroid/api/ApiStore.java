/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.naceyeve.liandroid.api;


import com.naceyeve.liandroid.bean.Ganhuo;
import com.naceyeve.liandroid.bean.Meizi;
import com.naceyeve.liandroid.bean.Zhihu;
import com.naceyeve.liandroid.bean.ZhihuDescriber;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

// @formatter:off

/**
 * Created by drakeet on 8/9/15.
 */
public interface ApiStore {
        //妹子
    @GET("api/data/{type}/{count}/{page}")
    Observable<ApiResponse<List<Meizi>>> getGirls(
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page
    );

    //知乎
    @GET("/api/4/news/latest")
    Observable<Zhihu> getLastDaily();

    @GET("/api/4/news/before/{date}")
    Observable<Zhihu> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    Observable<ZhihuDescriber> getZhihuStory(@Path("id") String id);
//
//    @GET("http://lab.zuimeia.com/wallpaper/category/1/?page_size=1")
//    Observable<ImageResponse> getImage();
    //干货
    /**
     * 获取发布干货的日期
     *
     * @return
     */
    @GET("/api/day/history")
    Observable<ApiResponse<List<String>>> getDate();

    /**
     * 获取最近5日干货网站数据
     *
     * @return
     */
    @GET("/api/history/content/5/1")
    Observable<ApiResponse<List<Ganhuo>>> getRecentlyData();
}
