<template>
  <a-layout>
    <editor-sidebar style="position: fixed"/>
    <a-layout style="margin-left: 80px">
      <a-layout-content style="margin: 16px 16px">
        <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
          <router-view/>
        </div>
        <the-footer/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, onMounted} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue';
import TheHeader from '@/components/the-header.vue';
import axios from "axios";

export default defineComponent({
  name: 'EditorView',
  components: {
    EditorSidebar,
    TheFooter,
    TheHeader,
  },
  setup() {
    console.log("main editor setup");
  }
});
</script>
