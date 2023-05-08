<template>
  <a-layout>
    <a-layout-content style="background-color: #FFFFFF; padding: 0 50px">
      <a-typography-title :level="2" style="margin-top: 20px">NPC 对话</a-typography-title>
      <a-auto-complete
          v-model:value="npcName"
          style="width: 200px"
          placeholder="输入 NPC ID"
          :options="searchOptions"
          @search="onSearch"
      >
      </a-auto-complete>
      <a-button @click="onCreate" :disabled="createButtonDisabled">Load/Create</a-button>
      <a-divider></a-divider>

      <template v-if="npcDisplayName">
        <span style="margin-bottom: 8px;">{{ npcDisplayName }}</span>
      </template>
      <a-divider></a-divider>

      <a-form
          ref="formRef"
          :model="currentNodeData"
          style="margin-top: 16px;"
          layout="horizontal"
          v-if="createButtonDisabled"
      >
        <a-form-item label="选择对话节点">
          <a-tree-select
              show-search
              allow-clear
              tree-default-expand-all
              style="width: 100%"
              v-model:value="currentNodeValue"
              :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
              :tree-data="treeData"
          >
            <template #title="{ title }">
              {{ title }}
            </template>
          </a-tree-select>
        </a-form-item>
        <a-divider></a-divider>
        <a-form-item v-if="currentNodeValue !== '' && currentNodeValue !== undefined "
                     :label="textInputLabel"
                     style="display: flex"
                     :rules="{required: true, message: 'Fields can not be null',trigger: 'change',}">
          <a-input v-model:value="currentNodeData.text" :placeholder="currentNodeData.text"
                   style="width: 100%"/>
        </a-form-item>
        <a-space
            v-for="(child, index) in currentNodeData.children"
            :key="child"
            layout="vertical"
            direction="vertical"
            style="width: 100%"
        >
          <a-form-item
              :label="childButtonLabel"
              :name="['children', index, 'value']"
              :rules="{required: false, message: 'Fields can not be null',trigger: 'change',}">
            <a-button @click="goToDirectory(child)">{{ child.title }}</a-button>
            <a-divider type="vertical"/>
            <MinusCircleOutlined @click="removeChild(child)"/>
          </a-form-item>
        </a-space>
        <a-form-item v-if="displayAddButton"
                     :label="childButtonLabel"
                     style="display: flex; width: 100%"
                     :rules="{required: false, message: 'Fields can not be null',trigger: 'change',}">
          <a-button type="dashed" @click="addChild()">
            <PlusOutlined/>
            增加
          </a-button>
        </a-form-item>
      </a-form>
    </a-layout-content>

  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, onMounted, watch, computed, Ref, reactive} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import {InboxOutlined} from '@ant-design/icons-vue';
import {message, TreeNode, TreeProps, TreeSelectProps} from 'ant-design-vue';
import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons-vue';

import axios from "axios";
import {UploadChangeParam} from 'ant-design-vue';
import TheDialog from "@/components/the-dialog.vue";


export default defineComponent({
  name: 'DialogEditorView',
  components: {
    MinusCircleOutlined,
    PlusOutlined,
    TheDialog,
    EditorSidebar,
    InboxOutlined,
  },
  setup() {
    console.log("dialog editor setup");
    // load and create npc
    const searchOptions = ref<{ value: string }[]>([]);
    const onSearch = (searchText: string) => {
      const encodedSearchText = encodeURIComponent(searchText);
      const url = `/editor/dialog/list?prefix=${encodedSearchText}`;
      axios.get(url).then(response => {
        searchOptions.value = response.data.map((item: string) => ({value: item}));
      });
    };
    const npcName = ref<string>();
    const createButtonDisabled = ref(false);
    const npcDisplayName = ref<string>();
    const treeData = ref<TreeSelectProps['treeData']>([]);
    const onCreate = () => {
      const encodedSearchText = encodeURIComponent(npcName.value || '');
      const url = `/editor/dialog/get?name=${encodedSearchText}`;
      axios.get(url).then(response => {
        console.log(response.data)
        let dialogJson = response.data;
        if (dialogJson == null || dialogJson == '') {
          return;
        } else {
          createButtonDisabled.value = true;
          npcDisplayName.value = dialogJson["name"];
          treeData.value = [createDialog(dialogJson["dialogs"], "root")]
        }
      });
    }

    // display npc
    const currentNodeData = reactive<{ type: string, text: string, children: any[] }>({
      type: '',
      text: '',
      children: [],
    });
    const textInputLabel = ref('')
    const childButtonLabel = ref('')
    const displayAddButton = ref(false)
    const currentNodeValue = ref('');
    const removeChild = (child: any) => {
      let currentDialog = getRootDialog()?.deepSearch(currentNodeValue.value);
      if (currentDialog) {
        currentDialog.removeChild(child.value);
        updateChildDataDisplay(currentDialog);
      }
    }
    const addChild = () => {
      let currentDialog = getRootDialog()?.deepSearch(currentNodeValue.value);
      if (currentDialog) {
        currentDialog.addChild();
        updateChildDataDisplay(currentDialog);
      }
    }
    // watch selected node and update data according to node
    watch(currentNodeValue, () => {
      let currentDialog = getRootDialog()?.deepSearch(currentNodeValue.value);
      if (currentDialog) {
        if (currentDialog.tabtype == "dialog") {
          textInputLabel.value = "NPC对话";
          childButtonLabel.value = "选项";
        }
        if (currentDialog.tabtype == "option") {
          textInputLabel.value = "选项";
          childButtonLabel.value = "NPC对话";
        }
        currentNodeData.text = currentDialog?.title;
        updateChildDataDisplay(currentDialog);
      }
    });
    watch(currentNodeData, () => {
      let currentDialog = getRootDialog()?.deepSearch(currentNodeValue.value);
      if (currentDialog) {
        if (currentDialog.tabtype == "dialog") {
          console.log("update dialog");
        }
        if (currentDialog.tabtype == "option") {
          console.log("update option");
        }
        currentDialog.title = currentNodeData.text;
        updateChildDataDisplay(currentDialog);
      }
    });
    const updateChildDataDisplay = (currentDialog: any) => {
      currentNodeData.children = currentDialog?.children;
      displayAddButton.value = (currentDialog?.children.length == 0 && currentDialog?.tabtype == 'option') || currentDialog?.tabtype == 'dialog';
    }
    const getRootDialog = () => {
      if (treeData.value) {
        return (treeData.value[0] as Dialog)
      }
      return null
    }
    const goToDirectory = (child: any) => {
      currentNodeValue.value = child.value;
    }


    return {
      npcName,
      npcDisplayName,
      searchOptions,
      createButtonDisabled,
      textInputLabel,
      childButtonLabel,
      //dialogs,
      onSearch,
      onCreate,
      removeChild,
      addChild,
      goToDirectory,
      displayAddButton,
      treeData,
      currentNodeValue,
      currentNodeData,
    };
  }

});

export function createDialog(json: any, directory: string): Dialog {
  let treeNode = new Dialog(directory + ".dialog", json["dialog"]);
  let optionCount = 0;
  if ("optionCount" in json) {
    optionCount = json["optionCount"];
  }
  for (let i = 0; i < optionCount; i++) {
    treeNode.children.push(createOption(json["option" + (i + 1)], directory + ".option" + (i + 1)))
  }
  return treeNode;
}

export function createOption(json: any, directory: string): Option {
  let treeNode = new Option(directory, json["text"]);
  if ("next" in json) {
    treeNode.children.push(createDialog(json["next"], directory));
  }
  return treeNode;
}

interface Node {
  value: string;
  title: string;
  children: any[];
  tabtype: string;

  deepSearch(searchValue: string): any;

  removeChild(searchValue: string): void;
}

export class Dialog implements Node {

  public value: string;
  public title: string;
  public children: Node[];
  public tabtype: string;

  constructor(value: string, title: string) {
    this.value = value;
    this.title = title;
    this.children = [];
    this.tabtype = "dialog";
  }

  deepSearch(searchValue: string): any {
    if (this.value == searchValue) {
      console.log("Search Success : " + searchValue)
      return this;
    }
    for (let i = 0; i < this.children.length; i++) {
      let searchResult = this.children[i].deepSearch(searchValue);
      if (searchResult != null) {
        return searchResult;
      }
    }
    return null;
  }

  addChild() {
    let dir = this.value.split(".dialog")[0] + ".option" + (this.children.length + 1).toString();
    this.children.push(new Option(dir, "..."))
  }

  removeChild(searchValue: string) {
    console.log("start searching " + searchValue)
    this.children = this.children.filter((child) => {
      console.log("search option " + child.value)
      if (child.value == searchValue) {
        return false;
      }
      return true;
    });

  }

}

export class Option implements Node {
  public value: string;
  public title: string;
  public children: Node[];
  public require: Requirement;
  public tabtype: string

  constructor(value: string, title: string) {
    this.value = value;
    this.title = title;
    this.children = [];
    this.tabtype = "option";
    this.require = Requirement.NO_REQUIREMENT;
  }

  deepSearch(searchValue: string): any {
    console.log("Search Success : " + searchValue)
    if (this.value == searchValue) {
      return this;
    }
    if (this.children.length > 0) {
      return this.children[0].deepSearch(searchValue);
    }
    return null;
  }

  removeChild(searchValue: string) {
    this.children = this.children.filter((child) => child.value != searchValue);
  }

  addChild() {
    let dir = this.value + ".dialog";
    if (this.children.length > 0) {
      this.children[0] = (new Dialog(dir, "..."))
    } else {
      this.children.push(new Dialog(dir, "..."))
    }
  }
}

export class Requirement {
  public static readonly NO_REQUIREMENT = new Requirement(0, "NONE")
  public level: number;
  public quest: string;

  // Constructor
  constructor(level: number, quest: string) {
    this.level = level;
    this.quest = quest;
  }
}


</script>
