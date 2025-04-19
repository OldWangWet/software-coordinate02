package com.oddfar.campus.business.controller;

import com.oddfar.campus.business.domain.UserLike;
import com.oddfar.campus.business.service.UserLikeService;
import com.oddfar.campus.common.annotation.ApiResource;
import com.oddfar.campus.common.domain.PageParam;
import com.oddfar.campus.common.domain.PageResult;
import com.oddfar.campus.common.domain.R;
import com.oddfar.campus.common.enums.ResBizTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/campus/userlike")
@ApiResource(name = "用户喜欢管理", appCode = "campus", resBizType = ResBizTypeEnum.BUSINESS)
public class UserLikeController {
    @Autowired
    private UserLikeService userLikeService;

    @PreAuthorize("@ss.resourceAuth()")
    @PutMapping(value = "/list/{page}/{size}", name = "查询用户喜欢列表")
    public R list(
            @PathVariable("page") int page,
            @PathVariable("size") int size,
            @RequestBody(required = false) UserLike userLike) {
        PageParam pageParam = new PageParam(page, size);
        PageResult<UserLike> pageResult = userLikeService.page(userLike, pageParam);
        return R.ok().put(pageResult);
    }

    @PreAuthorize("@ss.resourceAuth()")
    @GetMapping(value = "/{id}", name = "获取用户喜欢详细信息")
    public R getInfo(@PathVariable("id") Long id) {
        return R.ok(userLikeService.getById(id));
    }

    @PreAuthorize("@ss.resourceAuth()")
    @PostMapping(value = "/add", name = "新增用户喜欢")
    public R add(@Validated @RequestBody Long[] userLikes) {
        return R.ok(userLikeService.insertUserLikes(userLikes));
    }

//    @PreAuthorize("@ss.resourceAuth()")
//    @PutMapping(value = "/modify", name = "修改用户喜欢")
//    public R edit(@Validated @RequestBody UserLike userLike) {
//        return R.ok(userLikeService.updateUserLike(userLike));
//    }
//
//    @PreAuthorize("@ss.resourceAuth()")
//    @DeleteMapping(value = "/{ids}", name = "删除用户喜欢")
//    public R remove(@PathVariable Long[] ids) {
//        return R.ok(userLikeService.removeBatchByIds(Arrays.asList(ids)));
//    }
}