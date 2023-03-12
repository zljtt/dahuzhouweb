<template>
  <a-layout-header style="background: #fff; padding: 0">
    <a-breadcrumb style="margin: 16px 16px">
      <a-breadcrumb-item>User</a-breadcrumb-item>
      <a-breadcrumb-item>Bill</a-breadcrumb-item>
    </a-breadcrumb>
  </a-layout-header>
</template>

<script lang="ts">
import {defineComponent} from 'vue';

export default defineComponent({
  name: 'deck-header'
});
</script>