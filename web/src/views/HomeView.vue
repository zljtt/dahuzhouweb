<template>
  <a-layout>
    <deck-header/>
    <a-layout-content style="margin: 0 16px">
      <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
        Bill is a cat.
      </div>
    </a-layout-content>
    <the-footer/>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import DeckHeader from '@/components/deck-header.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'HomeView',
  components: {
    DeckHeader,
    TheFooter
  },
});
</script>
