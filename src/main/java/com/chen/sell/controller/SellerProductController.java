package com.chen.sell.controller;

import com.chen.sell.dataobject.ProductCategory;
import com.chen.sell.dataobject.ProductInfo;
import com.chen.sell.form.ProductForm;
import com.chen.sell.service.CategoryService;
import com.chen.sell.service.ProductService;
import com.chen.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    /**
     * 上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String ,Object> map){
        try {
            productService.onSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**
     * 下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String ,Object> map){
        try {
            productService.offSale(productId);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("/common/error",map);
        }
        map.put("url","sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }


    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map ){
        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productId);
        }
        //查询类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("/product/index",map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Validated ProductForm form, BindingResult bindingResult,Map<String,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }

        try {
            ProductInfo  productInfo = new ProductInfo();
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            }else{
                productInfo.setProductId(KeyUtil.genUniqueKey());
            }

            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/common/error",map);
        }
        return new ModelAndView("/product/index",map);
    }
}
