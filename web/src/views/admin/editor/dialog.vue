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
      <a-button @click="onCreate" :disabled="fileLoaded">加载/创建</a-button>

      <template v-if="fileLoaded">
        <a-divider></a-divider>
        <a-typography-title :level="3" v-model:content="npcDisplayName" editable style="margin-top: 5px"/>
        <a-typography-paragraph v-model:content="npcDescription" editable style="margin-top: 5px"/>
        <a-divider></a-divider>
        <a-form ref="formRef" :model="getCurrentDialog()" layout="horizontal">
          <a-form-item label="选择对话节点">
            <a-tree-select
                show-search
                allow-clear
                tree-default-expand-all
                style="width: 100%"
                defaultValue="root"
                v-model:value="currentNodeValue"
                :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
                :tree-data="npcDialogs"
            >
              <template #title="{ value, title }">
                {{ getParentOption(value, title) }}
              </template>
            </a-tree-select>
          </a-form-item>
          <a-divider></a-divider>
          <template
              v-if="currentNodeValue !== '' && currentNodeValue !== undefined && getCurrentDialog().title!== 'NO_DIALOG'">
            <a-form-item
                label="NPC对话"
                :rules="{required: true, message: '对话一定要有个头吧',trigger: 'change'}">
              <a-input v-model:value="getCurrentDialog().title"
                       :placeholder="getCurrentDialog().title"
                       style="width: 100%"/>
            </a-form-item>
            <a-space
                v-for="(option, index) in getCurrentDialog()?.options ?? []" :key="option"
                size=small direction="vertical"
                style="width: 100%; border-style: dashed; border-color: gainsboro; border-bottom: transparent"
            >
              <a-form layout=horizontal style="margin-left: 10px;">
                <a-row style="width: 100%" align="start">
                  <a-col :span="19" style="margin-top: 10px">
                    <a-form-item label="玩家选项">
                      <a-typography-paragraph v-model:content="option.text" editable style="margin-top: 5px"/>
                    </a-form-item>
                  </a-col>
                  <a-col :span="5">
                    <a-popconfirm
                        title="真的要删除这个选项吗？这会删除所有下层目录"
                        placement="topRight"
                        ok-text="确定"
                        cancel-text="取消"
                        @confirm="removeOption(index)"
                    >
                      <a-button type="dashed" danger style="float: right;">
                        删除选项
                        <MinusCircleOutlined/>
                      </a-button>
                    </a-popconfirm>
                  </a-col>
                </a-row>
                <a-form layout="inline">
                  <a-form-item label="等级需求" :rules="{required: false}">
                    <a-typography-paragraph v-model:content="option.requireLevel" editable
                                            style="margin-top: 5px; width: 100px"/>
                  </a-form-item>
                  <a-form-item label="任务需求" :rules="{required: false}">
                    <a-typography-paragraph v-model:content="option.requireQuest" editable
                                            style="margin-top: 5px; width: 100px"/>
                  </a-form-item>
                </a-form>
                <a-form layout="inline" style="min-height: 45px">
                  <a-form-item label="下一级NPC对话" :rules="{required: false}">
                    <template v-if="getCurrentDialog().children[index].title !== 'NO_DIALOG'">
                      <a-typography-paragraph
                          v-model:content="getCurrentDialog().children[index].title"
                          @click="goToDirectory(getCurrentDialog().children[index].value)"
                          editable style="margin-top: 5px"/>
                    </template>
                    <template v-if="getCurrentDialog().children[index].title === 'NO_DIALOG'">
                      <a-button type="dashed"
                                @click="addOptionDialog(index)">
                        <PlusOutlined/>
                        设立下级对话
                      </a-button>
                    </template>
                  </a-form-item>
                  <a-form-item>
                    <template v-if="getCurrentDialog().children[index].title !== 'NO_DIALOG'">
                      <a-popconfirm
                          title="真的要删除所有下层对话吗？"
                          ok-text="确定"
                          cancel-text="取消"
                          @confirm="clearOptionDialog(index)"
                      >
                        <MinusCircleOutlined style=""/>
                      </a-popconfirm>
                    </template>
                  </a-form-item>
                </a-form>
              </a-form>
            </a-space>
            <a-form-item
                :rules="{required: false, message: 'Fields can not be null',trigger: 'change',}">
              <a-button type="dashed" @click="addOption()" style="width: 100%">
                <PlusOutlined/>
                增加选项
              </a-button>
            </a-form-item>
            <a-divider></a-divider>
          </template>
        </a-form>
        <a-popconfirm
            title="真的要保存吗？这会覆盖原来的文件"
            placement="topLeft"
            ok-text="确定"
            cancel-text="取消"
            @confirm="sendJson()"
        >
          <a-button type="primary">保存</a-button>
        </a-popconfirm>
        <a-divider type="vertical"/>
        <a-popconfirm
            title="真的要永远删除这个NPC吗？"
            placement="topLeft"
            ok-text="确定"
            cancel-text="取消"
            @confirm="onDeleteFile()"
        >
          <a-button :disabled="!fileLoaded" type="primary" danger>删除NPC</a-button>
        </a-popconfirm>
      </template>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, ref, watch} from 'vue';
import EditorSidebar from '@/components/editor-sidebar.vue'; // @ is an alias to /src
import {InboxOutlined, MinusCircleOutlined, PlusOutlined} from '@ant-design/icons-vue';
import {message, TreeSelectProps} from 'ant-design-vue';

import axios from "axios";
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
    message.config({
      top: '100px',
      duration: 2,
      maxCount: 3,
      rtl: true,
      prefixCls: '',
    });
    console.log("dialog editor setup");
    // load and create npc
    const searchOptions = ref<{ value: string }[]>([]);
    const onSearch = (searchText: string) => {
      const encodedSearchText = encodeURIComponent(searchText);
      const url = `/editor/dialog/list?prefix=${encodedSearchText}`;
      axios.get(url).then(response => {
        if (response.data) {
          searchOptions.value = response.data.map((item: string) => ({value: item}));
        } else {
          searchOptions.value = [];
        }
      });
    };
    const npcName = ref<string>();
    const fileLoaded = ref(false);
    const npcDisplayName = ref<string>();
    const npcDescription = ref<string>('');
    const npcDialogs = ref<TreeSelectProps['treeData']>();
    watch(npcName, () => {
      fileLoaded.value = false;
    })
    const onCreate = () => {
      const key = 'createMessage';
      message.loading({content: "加载中...", key});
      const encodedSearchText = encodeURIComponent(npcName.value || '');
      const url = `/editor/dialog/get?name=${encodedSearchText}`;
      axios.get(url).then(response => {
        if (response.data.fileStatus == "NOT_FOUND") {
          npcDialogs.value = [new Dialog("root", "第一条对话")];
          npcDisplayName.value = '还没有名字';
          npcDescription.value = '还没有NPC设定';
          fileLoaded.value = true;
          message.loading({content: "创建新NPC", key});
          return;
        } else {
          try {
            let dialogJson = JSON.parse(response.data.content);
            if ("dialogs" in dialogJson) {
              npcDialogs.value = [createDialog(dialogJson["dialogs"], "root")]
            } else {
              npcDialogs.value = [new Dialog("root", "还没有对话")]
            }
            npcDisplayName.value = !("name" in dialogJson) || dialogJson["name"] == '' ?
                '还没有名字' : dialogJson["name"];
            npcDescription.value = !("description" in dialogJson) || dialogJson["description"] == '' ?
                '还没有NPC设定' : dialogJson["description"];
            fileLoaded.value = true;
            message.success({content: response.data.message, key});
          } catch (error) {
            message.success({content: response.data.message, key});
          }
        }
      });
    }


    // display npc
    const currentNodeValue = ref('');
    const removeOption = (index: number) => {
      let currentDialog = getCurrentDialog();
      if (currentDialog) {
        currentDialog.removeOption(index);
      }
    }
    const clearOptionDialog = (index: number) => {
      let currentDialog = getCurrentDialog();
      if (currentDialog) {
        currentDialog.removeDialog(index);
      }
    }
    const addOption = () => {
      let currentDialog = getCurrentDialog();
      if (currentDialog) {
        currentDialog.addOption();
      }
    }
    const addOptionDialog = (index: number) => {
      let currentDialog = getCurrentDialog();
      if (currentDialog) {
        currentDialog.addDialog(index);
      }
    }
    const getParentOption = (childValue: string, title: string) => {
      if (!npcDialogs.value || !childValue) {
        return "";
      }

      let txt = '';
      if (childValue != "root") {
        txt += "(" + (npcDialogs.value[0] as Dialog).searchParentOption(childValue) + ") ";
      }
      return txt + (title == "NO_DIALOG" ? "结束对话" : title);
    }
    const getCurrentDialog = () => {
      if (npcDialogs.value && npcDialogs.value.length > 0) {
        return (npcDialogs.value[0] as Dialog).deepSearch(currentNodeValue.value)
      }
      return new Dialog("", "NO_DIALOG");
    }
    const goToDirectory = (dir: string) => {
      currentNodeValue.value = dir;
    }

    const sendJson = () => {
      const key = 'saveMessage';

      message.loading({content: '加载中...', key})
      if (fileLoaded.value) {
        if (!npcDisplayName.value || npcDisplayName.value == '') {
          message.warn({content: 'NPC的名字不能为空', key});
          return;
        }
        if (!npcName.value) {
          message.warn({content: 'NPC的ID不能为空', key});
          return;
        }
        if (!npcDialogs.value) {
          message.warn({content: 'NPC不能没有对话', key});
          return;
        }
        let jsonStr = JSON.stringify({
          name: npcDisplayName.value == '还没有名字' ? '' : npcDisplayName.value,
          description: npcDescription.value == '还没有NPC设定' ? '' : npcDescription.value,
          dialogs: (npcDialogs.value[0] as Dialog).toJson()
        })
        try {
          const encodedSearchText = encodeURIComponent(npcName.value);
          const url = `/editor/dialog/upload?name=${encodedSearchText}`;
          axios.post(url, jsonStr, {
            headers: {'Content-Type': 'application/json'},
          }).then((result) => message.success({content: result.data.message, key}));
        } catch (error) {
          message.warn({content: '保存失败', key});
        }
        return;
      }
      message.warn({content: '还没加载或创建NPC', key});
    }
    const onDeleteFile = () => {
      const key = 'deleteMessage';
      message.loading({content: '删除中...', key})
      if (fileLoaded.value) {
        if (!npcName.value) {
          message.warn({content: 'NPC的ID不能为空', key});
          return;
        }
        try {
          const encodedSearchText = encodeURIComponent(npcName.value);
          const url = `/editor/dialog/delete?name=${encodedSearchText}`;
          axios.post(url, {
            headers: {'Content-Type': 'application/json'},
          }).then((result) => message.success({content: result.data.message, key}));
        } catch (error) {
          message.warn({content: '删除失败', key});
        }
      }
    }
    return {
      npcName,
      npcDisplayName,
      npcDescription,
      searchOptions,
      fileLoaded,
      onSearch,
      onCreate,
      removeOption,
      clearOptionDialog,
      addOption,
      goToDirectory,
      addOptionDialog,
      getParentOption,
      getCurrentDialog,
      sendJson,
      onDeleteFile,
      npcDialogs,
      currentNodeValue,
      // currentNodeData,
    };
  }

});

export function createDialog(json: any, directory: string): Dialog {
  let dialog = new Dialog(directory, json["dialog"]);
  let optionCount = 0;
  if ("optionCount" in json) {
    optionCount = json["optionCount"];
  }
  for (let i = 0; i < optionCount; i++) {
    let childJson = json["option" + (i + 1)];
    let nextDialogDir = directory + ".option" + (i + 1).toString();
    let child = new Option(nextDialogDir, childJson["text"]);
    if ("next" in childJson) {
      dialog.children.push(createDialog(childJson["next"], nextDialogDir));
    } else {
      dialog.children.push(new Dialog(nextDialogDir, "NO_DIALOG"));
    }
    if ("require" in childJson) {
      child.requireQuest = childJson["require"]["quest"];
      child.requireLevel = childJson["require"]["level"].toString();
    }
    dialog.options.push(child);
  }
  return dialog;
}


export class Dialog {
  public value: string;
  public title: string;
  public children: Dialog[];
  public options: Option[];

  constructor(value: string, title: string) {
    this.value = value;
    this.title = title;
    this.children = [];
    this.options = [];
  }

  toJson() {
    let buildJson: { [k: string]: any } = {};
    buildJson.dialog = this.title;
    if (this.options.length == 0) {
      return buildJson
    }
    buildJson.optionCount = this.options.length;
    for (let i = 0; i < this.options.length; i++) {
      let optionJson: { [k: string]: any } = {};
      optionJson.text = this.options[i].text;
      if (this.options[i].requireLevel != "" || this.options[i].requireQuest != "") {
        let level = Number(this.options[i].requireLevel);
        if (!level) {
          level = 0;
        }
        optionJson.require = {
          level: level,
          quest: this.options[i].requireQuest == "" ? "NONE" : this.options[i].requireLevel
        }
      }
      if (this.children[i].title != "NO_DIALOG") {
        optionJson.next = this.children[i].toJson()
      }
      buildJson["option" + (i + 1)] = optionJson
    }
    return buildJson
  }

  deepSearch(searchValue: string): Dialog | undefined {
    if (this.value == searchValue) {
      return this;
    }
    for (let i = 0; i < this.children.length; i++) {
      let searchResult = this.children[i].deepSearch(searchValue);
      if (searchResult != undefined) {
        return searchResult;
      }
    }
    return undefined;
  }

  searchParentOption(searchValue: string): string {
    for (let i = 0; i < this.children.length; i++) {
      if (this.children[i].value == searchValue) {
        return this.options[i].text;
      }
    }
    for (let i = 0; i < this.children.length; i++) {
      let searchResult = this.children[i].searchParentOption(searchValue);
      if (searchResult != "") {
        return searchResult;
      }
    }
    return "";
  }

  addOption() {
    let dir = this.value + ".option" + (this.children.length + 1).toString();
    this.options.push(new Option(dir, dir));
    this.children.push(new Dialog(dir, "NO_DIALOG"))
  }

  addDialog(index: number) {
    if (this.children.length > index) {
      this.children[index].title = this.children[index].value;
    }
  }

  removeDialog(index: number) {
    this.children[index] = new Dialog(this.children[index].value, "NO_DIALOG");
  }

  removeOption(index: number) {
    let deleteDir = this.options[index].dir;
    this.options = this.options.filter((option) => option.dir != deleteDir);
    this.children = this.children.filter((child) => child.value != deleteDir);
  }

}

export class Option {
  public dir: string;
  public text: string;
  public requireLevel: string;
  public requireQuest: string;

  constructor(dir: string, text: string) {
    this.dir = dir;
    this.text = text;
    this.requireLevel = "";
    this.requireQuest = "";
  }
}


</script>
