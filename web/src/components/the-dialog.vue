<template>
  <a-collapse>
    <a-collapse-panel :key="dialog.panelId" :header="dialog.dialog">
      <template v-for="option in dialog.options" :key="option.panelId">
        <a-collapse-panel :header="option.text">
          <template v-if="option.next">
            <the-dialog :dialog="option.next"/>
          </template>
        </a-collapse-panel>
      </template>
    </a-collapse-panel>
  </a-collapse>
</template>

<script lang="ts">
//import {Dialog} from '@/views/DialogEditorView.vue';
import {defineComponent, PropType} from 'vue';

export default defineComponent({
  name: 'the-dialog',
  props: {
    dialog: {
      //type: Object as PropType<Dialog>,
      required: true,
    },
  },
});
</script>