<template>
<el-form>
  <el-row>
    <el-button @click = "handleTest">test按钮</el-button>
    <input v-show="fileupShow" type="file" name="file" ref="fileInput" id="fileSelect" @change="update"/>

    <!--<el-button type="primary" @click = "handleUpload">主要按钮</el-button>-->
    <el-button @click = "fileOpen">上传文件</el-button>
    <el-button type="success" @click="handleUpload">成功按钮</el-button>
  </el-row>
  <el-table :data="tableData" height="550" border="true" style="width: 100%" v-if="tableData">
    <el-table-column label="name" width="180" prop="col1"></el-table-column>
    <el-table-column label="gender" width="180" prop="col2" ></el-table-column>
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
      methods:{
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
              console.log(rd);
              console.log(rd.data.table);
              this.tableData = rd.data.table;
              console.log(this.tableData);
            }).catch(function (error) {
              console.log(error);
            })
        }

      }
    }
</script>

<style scoped>

</style>
