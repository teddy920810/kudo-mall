(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-02a3"],{"/lCt":function(t,a,e){"use strict";var l=e("qXzc");e.n(l).a},"2nGb":function(t,a,e){"use strict";e.d(a,"b",function(){return n}),e.d(a,"f",function(){return r}),e.d(a,"a",function(){return o}),e.d(a,"e",function(){return i}),e.d(a,"c",function(){return c}),e.d(a,"g",function(){return m}),e.d(a,"d",function(){return u}),e.d(a,"h",function(){return s});var l=e("t3Un");function n(){return Object(l.a)({url:"/config/mall",method:"get"})}function r(t){return Object(l.a)({url:"/config/mall",method:"post",data:t})}function o(){return Object(l.a)({url:"/config/express",method:"get"})}function i(t){return Object(l.a)({url:"/config/express",method:"post",data:t})}function c(){return Object(l.a)({url:"/config/order",method:"get"})}function m(t){return Object(l.a)({url:"/config/order",method:"post",data:t})}function u(){return Object(l.a)({url:"/config/wx",method:"get"})}function s(t){return Object(l.a)({url:"/config/wx",method:"post",data:t})}},FT0L:function(t,a,e){"use strict";e.r(a);var l=e("2nGb"),n=e("rs3x"),r=e("X4fA"),o={name:"ConfigMail",data:function(){return{uploadPath:n.e,dataForm:{litemall_mall_name:"",litemall_mall_address:"",litemall_mall_phone:"",litemall_mall_email:"",litemall_mall_qrcode:""}}},computed:{headers:function(){return{"X-Litemall-Admin-Token":Object(r.a)()}}},created:function(){this.init()},methods:{init:function(){var t=this;Object(l.b)().then(function(a){t.dataForm=a.data.data})},cancel:function(){this.init()},update:function(){var t=this;Object(l.f)(this.dataForm).then(function(a){t.$notify.success({title:"成功",message:"商场配置成功"})}).catch(function(a){t.$notify.error({title:"失败",message:a.data.errmsg})})},uploadPicUrl:function(t){this.dataForm.litemall_mall_qrcode=t.data.url}}},i=(e("/lCt"),e("KHd+")),c=Object(i.a)(o,function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"app-container"},[e("el-form",{ref:"dataForm",attrs:{model:t.dataForm,"status-icon":"","label-width":"300px"}},[e("el-form-item",{attrs:{label:"商场名称",prop:"litemall_mall_name"}},[e("el-input",{model:{value:t.dataForm.litemall_mall_name,callback:function(a){t.$set(t.dataForm,"litemall_mall_name",a)},expression:"dataForm.litemall_mall_name"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"商场地址",prop:"litemall_mall_address"}},[e("el-input",{model:{value:t.dataForm.litemall_mall_address,callback:function(a){t.$set(t.dataForm,"litemall_mall_address",a)},expression:"dataForm.litemall_mall_address"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"联系电话",prop:"litemall_mall_phone"}},[e("el-input",{model:{value:t.dataForm.litemall_mall_phone,callback:function(a){t.$set(t.dataForm,"litemall_mall_phone",a)},expression:"dataForm.litemall_mall_phone"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"电子邮箱",prop:"litemall_mall_email"}},[e("el-input",{model:{value:t.dataForm.litemall_mall_email,callback:function(a){t.$set(t.dataForm,"litemall_mall_email",a)},expression:"dataForm.litemall_mall_email"}})],1),t._v(" "),e("el-form-item",{attrs:{label:"二维码"}},[e("el-upload",{staticClass:"avatar-uploader",attrs:{action:t.uploadPath,"show-file-list":!1,headers:t.headers,"on-success":t.uploadPicUrl,accept:".jpg,.jpeg,.png,.gif"}},[t.dataForm.litemall_mall_qrcode?e("img",{staticClass:"avatar",attrs:{src:t.dataForm.litemall_mall_qrcode}}):e("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),t._v(" "),e("el-form-item",[e("el-button",{on:{click:t.cancel}},[t._v("取消")]),t._v(" "),e("el-button",{attrs:{type:"primary"},on:{click:t.update}},[t._v("确定")])],1)],1)],1)},[],!1,null,"c93546a0",null);c.options.__file="mall.vue";a.default=c.exports},qXzc:function(t,a,e){},rs3x:function(t,a,e){"use strict";e.d(a,"c",function(){return n}),e.d(a,"a",function(){return r}),e.d(a,"d",function(){return o}),e.d(a,"b",function(){return i}),e.d(a,"e",function(){return c});var l=e("t3Un");function n(t){return Object(l.a)({url:"/storage/list",method:"get",params:t})}function r(t){return Object(l.a)({url:"/storage/create",method:"post",data:t})}function o(t){return Object(l.a)({url:"/storage/update",method:"post",data:t})}function i(t){return Object(l.a)({url:"/storage/delete",method:"post",data:t})}var c="https://www.kuanduhome.com/api/storage/create"}}]);