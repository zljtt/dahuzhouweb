<template>
  <a-layout>
    <span>Wiki</span>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, onMounted} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue'; // @ is an alias to /src
import axios from "axios";

export default defineComponent({
  name: 'EditorView',
  components: {
    EditorSidebar,
    TheFooter
  },
  setup() {
    console.log("server editor setup");
  }
});
</script>
