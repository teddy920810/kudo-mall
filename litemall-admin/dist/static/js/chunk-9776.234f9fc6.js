(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-9776"],{Mz3J:function(t,e,a){"use strict";Math.easeInOutQuad=function(t,e,a,i){return(t/=i/2)<1?a/2*t*t+e:-a/2*(--t*(t-2)-1)+e};var i=window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)};function n(t,e,a){var n=document.documentElement.scrollTop||document.body.parentNode.scrollTop||document.body.scrollTop,r=t-n,o=0;e=void 0===e?500:e;!function t(){o+=20,function(t){document.documentElement.scrollTop=t,document.body.parentNode.scrollTop=t,document.body.scrollTop=t}(Math.easeInOutQuad(o,n,r,e)),o<e?i(t):a&&"function"==typeof a&&a()}()}var r={name:"Pagination",props:{total:{required:!0,type:Number},page:{type:Number,default:1},limit:{type:Number,default:20},pageSizes:{type:Array,default:function(){return[10,20,30,50]}},layout:{type:String,default:"total, sizes, prev, pager, next, jumper"},background:{type:Boolean,default:!0},autoScroll:{type:Boolean,default:!0},hidden:{type:Boolean,default:!1}},computed:{currentPage:{get:function(){return this.page},set:function(t){this.$emit("update:page",t)}},pageSize:{get:function(){return this.limit},set:function(t){this.$emit("update:limit",t)}}},methods:{handleSizeChange:function(t){this.$emit("pagination",{page:this.currentPage,limit:t}),this.autoScroll&&n(0,800)},handleCurrentChange:function(t){this.$emit("pagination",{page:t,limit:this.pageSize}),this.autoScroll&&n(0,800)}}},o=(a("PelQ"),a("KHd+")),l=Object(o.a)(r,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"pagination-container",class:{hidden:t.hidden}},[a("el-pagination",t._b({attrs:{background:t.background,"current-page":t.currentPage,"page-size":t.pageSize,layout:t.layout,total:t.total},on:{"update:currentPage":function(e){t.currentPage=e},"update:pageSize":function(e){t.pageSize=e},"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}},"el-pagination",t.$attrs,!1))],1)},[],!1,null,"7d9f0a7c",null);l.options.__file="index.vue";e.a=l.exports},PelQ:function(t,e,a){"use strict";var i=a("X9ZH");a.n(i).a},QKMB:function(t,e,a){"use strict";a.r(e);var i=a("FyfS"),n=a.n(i),r=a("P2sY"),o=a.n(r),l=a("t3Un");var s=a("rs3x"),d=a("X4fA"),c={name:"Brand",components:{Pagination:a("Mz3J").a},data:function(){return{uploadPath:s.e,list:[],total:0,listLoading:!0,listQuery:{page:1,limit:20,id:void 0,name:void 0,sort:"add_time",order:"desc"},dataForm:{id:void 0,name:"",desc:"",floorPrice:void 0,picUrl:void 0},dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑",create:"创建"},rules:{name:[{required:!0,message:"品牌商名称不能为空",trigger:"blur"}]},downloadLoading:!1}},computed:{headers:function(){return{"X-Litemall-Admin-Token":Object(d.a)()}}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.listLoading=!0,function(t){return Object(l.a)({url:"/brand/list",method:"get",params:t})}(this.listQuery).then(function(e){t.list=e.data.data.list,t.total=e.data.data.total,t.listLoading=!1}).catch(function(){t.list=[],t.total=0,t.listLoading=!1})},handleFilter:function(){this.listQuery.page=1,this.getList()},resetForm:function(){this.dataForm={id:void 0,name:"",desc:"",floorPrice:void 0,picUrl:void 0}},handleCreate:function(){var t=this;this.resetForm(),this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){t.$refs.dataForm.clearValidate()})},uploadPicUrl:function(t){this.dataForm.picUrl=t.data.url},createData:function(){var t=this;this.$refs.dataForm.validate(function(e){e&&function(t){return Object(l.a)({url:"/brand/create",method:"post",data:t})}(t.dataForm).then(function(e){t.list.unshift(e.data.data),t.dialogFormVisible=!1,t.$notify.success({title:"成功",message:"创建成功"})}).catch(function(e){t.$notify.error({title:"失败",message:e.data.errmsg})})})},handleUpdate:function(t){var e=this;this.dataForm=o()({},t),this.dialogStatus="update",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},updateData:function(){var t=this;this.$refs.dataForm.validate(function(e){e&&function(t){return Object(l.a)({url:"/brand/update",method:"post",data:t})}(t.dataForm).then(function(){var e=!0,a=!1,i=void 0;try{for(var r,o=n()(t.list);!(e=(r=o.next()).done);e=!0){var l=r.value;if(l.id===t.dataForm.id){var s=t.list.indexOf(l);t.list.splice(s,1,t.dataForm);break}}}catch(t){a=!0,i=t}finally{try{!e&&o.return&&o.return()}finally{if(a)throw i}}t.dialogFormVisible=!1,t.$notify.success({title:"成功",message:"更新成功"})}).catch(function(e){t.$notify.error({title:"失败",message:e.data.errmsg})})})},handleDelete:function(t){var e=this;(function(t){return Object(l.a)({url:"/brand/delete",method:"post",data:t})})(t).then(function(a){e.$notify.success({title:"成功",message:"删除成功"});var i=e.list.indexOf(t);e.list.splice(i,1)}).catch(function(t){e.$notify.error({title:"失败",message:t.data.errmsg})})},handleDownload:function(){var t=this;this.downloadLoading=!0,Promise.all([a.e("chunk-0d49"),a.e("chunk-eb52")]).then(a.bind(null,"S/jZ")).then(function(e){e.export_json_to_excel2(["品牌商ID","品牌商名称","介绍","低价","品牌商图片"],t.list,["id","name","desc","floorPrice","picUrl"],"品牌商信息"),t.downloadLoading=!1})}}},u=(a("lq8Q"),a("KHd+")),m=Object(u.a)(c,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"请输入品牌商ID"},model:{value:t.listQuery.id,callback:function(e){t.$set(t.listQuery,"id",e)},expression:"listQuery.id"}}),t._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{clearable:"",placeholder:"请输入品牌商名称"},model:{value:t.listQuery.name,callback:function(e){t.$set(t.listQuery,"name",e)},expression:"listQuery.name"}}),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["GET /admin/brand/list"],expression:"['GET /admin/brand/list']"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.handleFilter}},[t._v("查找")]),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/brand/create"],expression:"['POST /admin/brand/create']"}],staticClass:"filter-item",attrs:{type:"primary",icon:"el-icon-edit"},on:{click:t.handleCreate}},[t._v("添加")]),t._v(" "),a("el-button",{staticClass:"filter-item",attrs:{loading:t.downloadLoading,type:"primary",icon:"el-icon-download"},on:{click:t.handleDownload}},[t._v("导出")])],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],attrs:{data:t.list,"element-loading-text":"正在查询中。。。",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"品牌商ID",prop:"id"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"品牌商名称",prop:"name"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",property:"picUrl",label:"品牌商图片"},scopedSlots:t._u([{key:"default",fn:function(e){return[e.row.picUrl?a("img",{attrs:{src:e.row.picUrl,width:"80"}}):t._e()]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center","min-width":"400px",label:"介绍",prop:"desc"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"底价",prop:"floorPrice"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"200","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/brand/update"],expression:"['POST /admin/brand/update']"}],attrs:{type:"primary",size:"mini"},on:{click:function(a){t.handleUpdate(e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{directives:[{name:"permission",rawName:"v-permission",value:["POST /admin/brand/delete"],expression:"['POST /admin/brand/delete']"}],attrs:{type:"danger",size:"mini"},on:{click:function(a){t.handleDelete(e.row)}}},[t._v("删除")])]}}])})],1),t._v(" "),a("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total,page:t.listQuery.page,limit:t.listQuery.limit},on:{"update:page":function(e){t.$set(t.listQuery,"page",e)},"update:limit":function(e){t.$set(t.listQuery,"limit",e)},pagination:t.getList}}),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"dataForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{rules:t.rules,model:t.dataForm,"status-icon":"","label-position":"left","label-width":"100px"}},[a("el-form-item",{attrs:{label:"品牌商名称",prop:"name"}},[a("el-input",{model:{value:t.dataForm.name,callback:function(e){t.$set(t.dataForm,"name",e)},expression:"dataForm.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"介绍",prop:"simpleDesc"}},[a("el-input",{model:{value:t.dataForm.desc,callback:function(e){t.$set(t.dataForm,"desc",e)},expression:"dataForm.desc"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"品牌商图片",prop:"picUrl"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{headers:t.headers,action:t.uploadPath,"show-file-list":!1,"on-success":t.uploadPicUrl,accept:".jpg,.jpeg,.png,.gif"}},[t.dataForm.picUrl?a("img",{staticClass:"avatar",attrs:{src:t.dataForm.picUrl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),t._v(" "),a("el-form-item",{attrs:{label:"底价",prop:"floorPrice"}},[a("el-input",{model:{value:t.dataForm.floorPrice,callback:function(e){t.$set(t.dataForm,"floorPrice",e)},expression:"dataForm.floorPrice"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),"create"==t.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:t.createData}},[t._v("确定")]):a("el-button",{attrs:{type:"primary"},on:{click:t.updateData}},[t._v("确定")])],1)],1)],1)},[],!1,null,"1d06212e",null);m.options.__file="brand.vue";e.default=m.exports},X9ZH:function(t,e,a){},lq8Q:function(t,e,a){"use strict";var i=a("ohMJ");a.n(i).a},ohMJ:function(t,e,a){},rs3x:function(t,e,a){"use strict";a.d(e,"c",function(){return n}),a.d(e,"a",function(){return r}),a.d(e,"d",function(){return o}),a.d(e,"b",function(){return l}),a.d(e,"e",function(){return s});var i=a("t3Un");function n(t){return Object(i.a)({url:"/storage/list",method:"get",params:t})}function r(t){return Object(i.a)({url:"/storage/create",method:"post",data:t})}function o(t){return Object(i.a)({url:"/storage/update",method:"post",data:t})}function l(t){return Object(i.a)({url:"/storage/delete",method:"post",data:t})}var s="https://www.kuanduhome.com/api/admin/storage/create"}}]);