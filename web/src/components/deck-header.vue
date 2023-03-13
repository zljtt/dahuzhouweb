<template>
  <a-collapse v-model:activeKey="activeKey">
    <a-collapse-panel key="1" v-if="deckData" :header="deckData.name" :show-arrow="false"
                      style="-webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none;">
      <p>Description: {{ deckData.description }}</p>
      <p>Format: {{ deckData.format }}</p>
      <p>Public: {{ deckData.isPublic }}</p>
    </a-collapse-panel>
  </a-collapse>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";

export default defineComponent({
  name: 'deck-header',
  setup() {
    console.log("setup");
    const activeKey = ref(['1']);
    const deckData = ref();

    onMounted(() => {
      axios.get("/deck?name=Atraxa").then(response => {
        deckData.value = response.data;
        console.log(deckData.value)
      });
    })
    return {
      activeKey,
      deckData
    }
  }
});
</script>