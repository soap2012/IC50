<template>
<el-form>
  <el-row>
    <!--<el-button @click = "handleTest">test按钮</el-button>-->
    <el-button type="primary" icon="el-icon-download" @click="downlaodTemplate">1、下载模板</el-button>
    <input v-show="fileupShow" type="file" name="file" ref="fileInput" id="fileSelect" @change="update"/>

    <!--<el-button type="primary" @click = "handleUpload">主要按钮</el-button>-->
    <el-button @click = "fileOpen" icon="el-icon-upload">2、上传文件</el-button>
    <el-button type="success" @click="handleUpload" icon="el-icon-circle-check-outline">3、开始计算</el-button>
    <!--<el-button type="success" icon="el-icon-download">4、下载结果</el-button>-->

  </el-row>
  <el-table :data="tableData" height="550" border="true" v-if="tableData" align="center">
    <el-table-column label="浓度" width="180" prop="col1"></el-table-column>
    <el-table-column label="浓度对数" width="180" prop="col2" ></el-table-column>
    <el-table-column label="Cell viability" width="180" prop="col3" ></el-table-column>
    <el-table-column label="SD" width="180" prop="col4" ></el-table-column>
    <el-table-column label="N" width="180" prop="col5" ></el-table-column>
  </el-table>
</el-form>
</template>

<script>
  import axios from 'axios';
    export default {
      data(){
        return{
          file:'',
          tableData:[]
        };
      },
        name: "IC50Compute",
      mounted(){
        this.$notify({
          title: '提示',
          message: '请下载模板并严格按照模板上传',
          duration: 0
        });
      },
      methods:{
        downlaodTemplate(){
          window.location.href="/arithmetic/downloadTemplate";
          // axios.get('/arithmetic/downloadTemplate',{responseType: 'arraybuffer'}).then((response) => {
          //   //创建一个blob对象,file的一种
          //   let blob = new Blob([response.data], { type: 'application/x-xls' })
          //   let link = document.createElement('a')
          //   link.href = window.URL.createObjectURL(blob)
          //   //配置下载的文件名
          //   link.download = fileNames[scheduleType] + '_' + response.headers.datestr + '.xls'
          //   link.click()
          // })
        },
        update(){
          this.$message("上传成功");
        },
        fileOpen(){
          let fileSelect = document.getElementById("fileSelect");
          fileSelect.click();
        },
          async handleTest(){
            axios.get('/arithmetic/test').then(function (response) {
              console.log(response);
            }).catch(function (error) {
              console.log(error);
            });
          },
        // getFile($event){
        //   this.file = $event.target.file[0];
        // },
        async handleUpload(){
            let da = new FormData();
            da.append("file",this.$refs.fileInput.files[0]);
            axios.post('/arithmetic/uploadFile',da).then( (response) => {
              const rd = response.data;
              if(rd.code == 200){
                console.log(rd);
                console.log(rd.data.table);
                this.tableData = rd.data.table;
                console.log(this.tableData);
                this.$message({message:"计算成功",type:'success',showClose:true});
              }else {
                this.$message({message:rd.msg,type:'error',showClose:true});
              }
            }).catch(function (error) {
              console.log(error);
            })
        }

      }
    }
</script>

<style scoped>

</style>
