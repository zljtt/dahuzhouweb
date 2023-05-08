<template>
  <a-layout>
    <span>File</span>
    <a-upload-dragger v-model:fileList="fileList"
                      name="file"
                      action="http://localhost:25565/editor/file/texture"
                      :multiple="false"
                      @change="handleChange"
                      @drop="handleDrop">
      <p class="ant-upload-drag-icon">
        <inbox-outlined></inbox-outlined>
      </p>
      <p class="ant-upload-text">Click or drag file to this area to upload</p>
      <p class="ant-upload-hint">
        Support for a single or bulk upload. Strictly prohibit from uploading company data or other
        band files
      </p>
    </a-upload-dragger>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, onMounted} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue'; // @ is an alias to /src
import {InboxOutlined} from '@ant-design/icons-vue';
import {message} from 'ant-design-vue';

import axios from "axios";
import {UploadChangeParam} from 'ant-design-vue';

export default defineComponent({
  name: 'EditorView',
  components: {
    EditorSidebar,
    TheFooter,
    InboxOutlined,
  },
  setup() {
    console.log("file editor setup");
    const handleChange = (info: UploadChangeParam) => {
      const status = info.file.status;
      if (status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (status === 'done') {
        message.success(`${info.file.name} file uploaded successfully.`);
      } else if (status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    }
    return {
      handleChange,
      fileList: ref([]),
      handleDrop: (e: DragEvent) => {
        console.log(e);
      },
    };
  }
});
</script>
