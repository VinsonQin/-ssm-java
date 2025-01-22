
var currentProductId;

function findAll() {

    $.ajax({
        url: 'product/findAll',
        type: "POST",
        success: function (list) {

            $("#dataTable tr.datarow").remove();

            $.each(list, function (index, p) {
                //console.log(p);
                var str = "<tr id=tr" + p.productId + " class=\"datarow\">" +
                        "<td>" + p.productId + "</td>" +
                        "<td>" + p.productName + "</td>" +
                        "<td>" + p.productPrice + "</td>" +
                        "<td>" + p.productUnit + "</td>" +
                        "<td>" + p.productStock + "</td>" +
                        "<td>" +
                        "<a href=\"javascript:popupEdit(" + p.productId + ");\">编辑</a>&nbsp;&nbsp;" +
                        "<a href=\"javascript:delete_product(" + p.productId + ");\">删除</a>" +
                        "</td></tr>";

                $("#dataTable").append(str);
            });
        },
        error: function (req, status, error) {
            alert("Ajax请求失败！" + error);
        }
    });
}

function popupEdit(pid) {

    currentProductId = pid;
    $.ajax({
        url: 'product/get_product_to_edit',
        type: 'POST',
        data: {proId: pid}, //发送一个请求参数，参数名为proId，参数值为传入的pid变量的值
        success: function (pro) {
            $("#inputId").val(pro.productId);
            $("#inputName").val(pro.productName);
            $("#inputPrice").val(pro.productPrice);
            $("#inputUnit").val(pro.productUnit);
            $("#inputStock").val(pro.productStock);
            $('#editModal').modal('show');
        },
        error: function (req, status, error) {
            alert("Ajax请求失败，错误：" + error);
        }
    });
}

function popupAdd() {
    $('#addModal').modal('show');
    $("#addForm")[0].reset();
}

//定义一个函数,根据编号删除产品
function delete_product(pid) {
    $.ajax({
        url: 'product/delete',
        type: 'POST',
        data: {proId: pid}, //发送一个请求参数，参数名为proId，参数值为传入的pid变量的值
        success: function () {
            //如果能执行到success，说明后台删除成功，这里同时将表格中对应的数据行删除
            $("#tr" + pid).remove(); // remove是删除当前元素和其内容
        },
        error: function (req, status, error) {
            alert("Ajax请求失败，错误：" + error);
        }
    });
}

$(document).ready(function () {

    $("#btnEdit").click(function () {
        $.ajax({
            url: 'product/edit_product',
            type: 'POST',
            data: $("#editForm").serialize(),
            success: function () {
                $("#tr" + currentProductId).children().eq(1).html($("#inputName").val());
                $("#tr" + currentProductId).children().eq(2).html($("#inputPrice").val());
                $("#tr" + currentProductId).children().eq(3).html($("#inputUnit").val());
                $("#tr" + currentProductId).children().eq(4).html($("#inputStock").val());
                $('#editModal').modal('hide');
            },
            error: function (req, status, error) {
                alert("Ajax请求失败，错误：" + error);
            }
        });
    });

    $("#btnAdd").click(function () {
        $.ajax({
            url: 'product/add_product',
            type: 'POST',
            data: $("#addForm").serialize(),
            success: function (proId) {
                var p = {
                    productId: proId,
                    productName: $("#proName").val(),
                    productPrice: $("#proPrice").val(),
                    productUnit: $("#proUnit").val(),
                    productStock: $("#proStock").val()
                }; 

                var str = "<tr id=tr" + p.productId + " class=\"datarow\">" +
                        "<td>" + p.productId + "</td>" +
                        "<td>" + p.productName + "</td>" +
                        "<td>" + p.productPrice + "</td>" +
                        "<td>" + p.productUnit + "</td>" +
                        "<td>" + p.productStock + "</td>" +
                        "<td>" +
                        "<a href=\"javascript:popupEdit(" + p.productId + ");\">编辑</a>&nbsp;&nbsp;" +
                        "<a href=\"javascript:delete_product(" + p.productId + ");\">删除</a>" +
                        "</td></tr>";

                $("#dataTable").append(str);

                $("#addModal").modal("hide");
            },
            error: function (req, status, error) {
                alert("Ajax请求失败，错误：" + error);
            }
        });
    });
});