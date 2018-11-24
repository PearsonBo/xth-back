package com.xth.controller;

import com.xth.model.base.PackVo;
import com.xth.model.tree.TreeNode;
import com.xth.service.MenuItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2017/12/28
 */
@RestController
@RequestMapping(MenuItemController.VIEW_PREFIX)
@Api(tags = {"权限列表接口"})
public class MenuItemController {

    public static final String VIEW_PREFIX = "/security/rest/menuItem";

    private static final String GET_MENU_TREE_BY_OPERATOR_ID = "/getMenuTreeByOperatorId";

    private static final String MENU_ITEM_TREE = "/menuItemTree";

    @Autowired
    private MenuItemService menuItemService;

    @RequestMapping(value = MENU_ITEM_TREE, method = RequestMethod.GET)
    @ApiOperation(value = "查询所有权限接口", notes = "返回权限树")
    public PackVo<TreeNode> menuItemTree() {
        PackVo<TreeNode> packVo = new PackVo<>();
        packVo.setVo(menuItemService.menuItemTree());
        return packVo;
    }

    @ResponseBody
    @RequestMapping(value = GET_MENU_TREE_BY_OPERATOR_ID, method = RequestMethod.GET)
    @ApiOperation(value = "根据操作人id查询权限接口", notes = "参数demo: operatorId:1")
    public PackVo<TreeNode> getMenuTreeByOperatorId(@RequestParam(value = "operatorId") Long operatorId) {
        PackVo<TreeNode> packVo = new PackVo<>();
        if (operatorId != null) {
            packVo.setVo(menuItemService.getMenuTreeByOperatorId(operatorId));
        }
        return packVo;
    }

}
