(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-c4b8"],{GqbN:function(e,t,n){"use strict";n.r(t);var o=n("xMja"),r=n("rs3x"),i=n("mRed"),a=n("XJYT"),s=n("X4fA"),u={name:"ProductCreate",components:{Editor:i.a},data:function(){return{uploadPath:r.e,newKeywordVisible:!1,newKeyword:"",keywords:[],seriesList:[],product:{picUrl:"",gallery:[]},rules:{name:[{required:!0,message:"产品名称不能为空",trigger:"blur"}]},editorInit:{language:"zh_CN",convert_urls:!1,plugins:["advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount"],toolbar:["searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample","hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen"],images_upload_handler:function(e,t,n){var o=new FormData;o.append("file",e.blob()),Object(r.a)(o).then(function(e){t(e.data.data.url)}).catch(function(){n("上传失败，请重新上传")})}}}},computed:{headers:function(){return{"X-Litemall-Admin-Token":Object(s.a)()}}},created:function(){this.init()},methods:{init:function(){var e=this;Object(o.f)().then(function(t){e.seriesList=t.data.data.seriesList})},handleSeriesChange:function(e){this.product.seriesId=e[e.length-1]},handleCancel:function(){this.$router.push({path:"/product/product"})},handlePublish:function(){var e=this;Object(o.a)(this.product).then(function(t){e.$notify.success({title:"成功",message:"创建成功"}),e.$router.push({path:"/product/list"})}).catch(function(e){a.MessageBox.alert("业务错误："+e.data.errmsg,"警告",{confirmButtonText:"确定",type:"error"})})},handleClose:function(e){this.keywords.splice(this.keywords.indexOf(e),1),this.product.keywords=this.keywords.toString()},showInput:function(){var e=this;this.newKeywordVisible=!0,this.$nextTick(function(t){e.$refs.newKeywordInput.$refs.input.focus()})},handleInputConfirm:function(){var e=this.newKeyword;e&&(this.keywords.push(e),this.product.keywords=this.keywords.toString()),this.newKeywordVisible=!1,this.newKeyword=""},uploadPicUrl:function(e){this.product.picUrl=e.data.url},uploadOverrun:function(){this.$message({type:"error",message:"上传文件个数超出限制!最多上传5张图片!"})},handleGalleryUrl:function(e,t,n){0===e.errno&&this.product.gallery.push(e.data.url)},handleRemove:function(e,t){for(var n=0;n<this.product.gallery.length;n++){var o;o=void 0===e.response?e.url:e.response.data.url,this.product.gallery[n]===o&&this.product.gallery.splice(n,1)}}}},l=(n("xryu"),n("KHd+")),c=Object(l.a)(u,function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:"app-container"},[n("el-card",{staticClass:"box-card"},[n("h3",[e._v("产品介绍")]),e._v(" "),n("el-form",{ref:"product",attrs:{rules:e.rules,model:e.product,"label-width":"150px"}},[n("el-form-item",{attrs:{label:"产品名称",prop:"name"}},[n("el-input",{model:{value:e.product.name,callback:function(t){e.$set(e.product,"name",t)},expression:"product.name"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"产品图片"}},[n("el-upload",{staticClass:"avatar-uploader",attrs:{action:e.uploadPath,"show-file-list":!1,headers:e.headers,"on-success":e.uploadPicUrl,accept:".jpg,.jpeg,.png,.gif"}},[e.product.picUrl?n("img",{staticClass:"avatar",attrs:{src:e.product.picUrl}}):n("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),e._v(" "),n("el-form-item",{attrs:{label:"宣传画廊"}},[n("el-upload",{attrs:{action:e.uploadPath,limit:5,headers:e.headers,"on-exceed":e.uploadOverrun,"on-success":e.handleGalleryUrl,"on-remove":e.handleRemove,multiple:"",accept:".jpg,.jpeg,.png,.gif","list-type":"picture-card"}},[n("i",{staticClass:"el-icon-plus"})])],1),e._v(" "),n("el-form-item",{attrs:{label:"关键字"}},[e._l(e.keywords,function(t){return n("el-tag",{key:t,attrs:{closable:"",type:"primary"},on:{close:function(n){e.handleClose(t)}}},[e._v("\n          "+e._s(t)+"\n        ")])}),e._v(" "),e.newKeywordVisible?n("el-input",{ref:"newKeywordInput",staticClass:"input-new-keyword",on:{blur:e.handleInputConfirm},nativeOn:{keyup:function(t){return"button"in t||!e._k(t.keyCode,"enter",13,t.key,"Enter")?e.handleInputConfirm(t):null}},model:{value:e.newKeyword,callback:function(t){e.newKeyword=t},expression:"newKeyword"}}):n("el-button",{staticClass:"button-new-keyword",attrs:{type:"primary"},on:{click:e.showInput}},[e._v("+ 增加")])],2),e._v(" "),n("el-form-item",{attrs:{label:"所属分类"}},[n("el-cascader",{attrs:{options:e.seriesList,"expand-trigger":"hover"},on:{change:e.handleSeriesChange}})],1),e._v(" "),n("el-form-item",{attrs:{label:"购买链接"}},[n("el-input",{model:{value:e.product.buyLink,callback:function(t){e.$set(e.product,"buyLink",t)},expression:"product.buyLink"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"产品简介"}},[n("el-input",{attrs:{type:"textarea"},model:{value:e.product.brief,callback:function(t){e.$set(e.product,"brief",t)},expression:"product.brief"}})],1),e._v(" "),n("el-form-item",{attrs:{label:"产品详细介绍"}},[n("editor",{attrs:{init:e.editorInit},model:{value:e.product.detail,callback:function(t){e.$set(e.product,"detail",t)},expression:"product.detail"}})],1)],1)],1),e._v(" "),n("div",{staticClass:"op-container"},[n("el-button",{on:{click:e.handleCancel}},[e._v("取消")]),e._v(" "),n("el-button",{attrs:{type:"primary"},on:{click:e.handlePublish}},[e._v("上架")])],1)],1)},[],!1,null,null,null);c.options.__file="create.vue";t.default=c.exports},HziX:function(e,t,n){"use strict";(function(e){n.d(t,"a",function(){return r});var o=function(){return"undefined"!=typeof window?window:e},r=function(){var e=o();return e&&e.tinymce?e.tinymce:null}}).call(this,n("yLpj"))},Y45g:function(e,t,n){},mRed:function(e,t,n){"use strict";var o=["onActivate","onAddUndo","onBeforeAddUndo","onBeforeExecCommand","onBeforeGetContent","onBeforeRenderUI","onBeforeSetContent","onBeforePaste","onBlur","onChange","onClearUndos","onClick","onContextMenu","onCopy","onCut","onDblclick","onDeactivate","onDirty","onDrag","onDragDrop","onDragEnd","onDragGesture","onDragOver","onDrop","onExecCommand","onFocus","onFocusIn","onFocusOut","onGetContent","onHide","onInit","onKeyDown","onKeyPress","onKeyUp","onLoadContent","onMouseDown","onMouseEnter","onMouseLeave","onMouseMove","onMouseOut","onMouseOver","onMouseUp","onNodeChange","onObjectResizeStart","onObjectResized","onObjectSelected","onPaste","onPostProcess","onPostRender","onPreProcess","onProgressState","onRedo","onRemove","onReset","onSaveContent","onSelectionChange","onSetAttrib","onSetContent","onShow","onSubmit","onUndo","onVisualAid"],r=function(e){return-1!==o.indexOf(e)},i=function(e,t,n){var o=t.$props.value?t.$props.value:"",i=t.$props.initialValue?t.$props.initialValue:"";n.setContent(o||i),t.$listeners.input&&function(e,t){var n,o=e.$props.modelEvents?e.$props.modelEvents:null,r=Array.isArray(o)?o.join(" "):o;e.$watch("value",function(e,o){t&&"string"==typeof e&&e!==n&&e!==o&&(t.setContent(e),n=e)}),t.on(r||"change keyup undo redo",function(){n=t.getContent(),e.$emit("input",n)})}(t,n),function(e,t,n){Object.keys(t).filter(r).forEach(function(o){var r=t[o];"function"==typeof r&&("onInit"===o?r(e,n):n.on(o.substring(2),function(e){return r(e,n)}))})}(e,t.$listeners,n)},a=0,s=function(e){var t=(new Date).getTime();return e+"_"+Math.floor(1e9*Math.random())+ ++a+String(t)},u=function(e){return void 0===e||""===e?[]:Array.isArray(e)?e:e.split(" ")},l=n("HziX"),c={apiKey:String,cloudChannel:String,id:String,init:Object,initialValue:String,inline:Boolean,modelEvents:[String,Array],plugins:[String,Array],tagName:String,toolbar:[String,Array],value:String,disabled:Boolean},d=Object.assign||function(e){for(var t,n=1,o=arguments.length;n<o;n++)for(var r in t=arguments[n])Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r]);return e},p={listeners:[],scriptId:s("tiny-script"),scriptLoaded:!1},f=function(e){return function(){var t=d({},e.$props.init,{readonly:e.$props.disabled,selector:"#"+e.elementId,plugins:function(e,t){return u(e).concat(u(t))}(e.$props.init&&e.$props.init.plugins,e.$props.plugins),toolbar:e.$props.toolbar||e.$props.init&&e.$props.init.toolbar,inline:e.inlineEditor,setup:function(t){e.editor=t,t.on("init",function(n){return i(n,e,t)}),e.$props.init&&"function"==typeof e.$props.init.setup&&e.$props.init.setup(t)}});(function(e){return null!==e&&"textarea"===e.tagName.toLowerCase()})(e.element)&&(e.element.style.visibility=""),Object(l.a)().init(t)}},h={props:c,created:function(){this.elementId=this.$props.id||s("tiny-vue"),this.inlineEditor=this.$props.init&&this.$props.init.inline||this.$props.inline},watch:{disabled:function(){this.editor.setMode(this.disabled?"readonly":"design")}},mounted:function(){if(this.element=this.$el,null!==Object(l.a)())f(this)();else if(this.element){var e=this.element.ownerDocument,t=this.$props.cloudChannel?this.$props.cloudChannel:"stable",n=this.$props.apiKey?this.$props.apiKey:"";!function(e,t,n,o){e.scriptLoaded?o():(e.listeners.push(o),t.getElementById(e.scriptId)||function(e,t,n,o){var r=t.createElement("script");r.type="application/javascript",r.id=e,r.addEventListener("load",o),r.src=n,t.head.appendChild(r)}(e.scriptId,t,n,function(){e.listeners.forEach(function(e){return e()}),e.scriptLoaded=!0}))}(p,e,"https://cloud.tinymce.com/"+t+"/tinymce.min.js?apiKey="+n,f(this))}},beforeDestroy:function(){null!==Object(l.a)()&&Object(l.a)().remove(this.editor)},render:function(e){return this.inlineEditor?function(e,t,n){return e(n||"div",{attrs:{id:t}})}(e,this.elementId,this.$props.tagName):function(e,t){return e("textarea",{attrs:{id:t},style:{visibility:"hidden"}})}(e,this.elementId)}};t.a=h},rs3x:function(e,t,n){"use strict";n.d(t,"c",function(){return r}),n.d(t,"a",function(){return i}),n.d(t,"d",function(){return a}),n.d(t,"b",function(){return s}),n.d(t,"e",function(){return u});var o=n("t3Un");function r(e){return Object(o.a)({url:"/storage/list",method:"get",params:e})}function i(e){return Object(o.a)({url:"/storage/create",method:"post",data:e})}function a(e){return Object(o.a)({url:"/storage/update",method:"post",data:e})}function s(e){return Object(o.a)({url:"/storage/delete",method:"post",data:e})}var u="https://www.kuanduhome.com/api/storage/create"},xMja:function(e,t,n){"use strict";n.d(t,"e",function(){return r}),n.d(t,"b",function(){return i}),n.d(t,"a",function(){return a}),n.d(t,"c",function(){return s}),n.d(t,"d",function(){return u}),n.d(t,"f",function(){return l});var o=n("t3Un");function r(e){return Object(o.a)({url:"/product/list",method:"get",params:e})}function i(e){return Object(o.a)({url:"/product/delete",method:"post",data:e})}function a(e){return Object(o.a)({url:"/product/create",method:"post",data:e})}function s(e){return Object(o.a)({url:"/product/detail",method:"get",params:{id:e}})}function u(e){return Object(o.a)({url:"/product/update",method:"post",data:e})}function l(){return Object(o.a)({url:"/product/series",method:"get"})}},xryu:function(e,t,n){"use strict";var o=n("Y45g");n.n(o).a}}]);