<html>
<#include "../common/head.ftl">
<body>
<div id = "wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容区域-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label >名称</label>
                            <input name="productName" type="text" class="form-control" value="${(product.productName!'')}" />
                        </div>

                        <div class="form-group">
                            <label >价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(product.productPrice!'')}" />
                        </div>

                        <div class="form-group">
                            <label >库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(product.productStock!'')}" />
                        </div>

                        <div class="form-group">
                            <label >描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(product.productDescription!'')}" />
                        </div>

                        <div class="form-group">
                            <label >图片</label>
                            <img height="100" width="100" src="${(product.productIcon!'')}">
                            <input name="productIcon" type="text" class="form-control" value="${(product.productIcon!'')}" />
                        </div>

                        <div class="form-group">
                            <label >类目</label>
                            <#--<input name="productDescription" type="text" class="form-control" value="${(product.productDescription!'')}" />-->
                            <select name="categoryType" class="form-group">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                    <#if (productInfo.categoryType)??&& productInfo.categoryType == category.categoryType>
                                        selected
                                    </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        </div> <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>


</body>
</html>

