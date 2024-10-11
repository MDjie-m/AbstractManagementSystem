<template>
  <div v-if="!item.hidden">
    <template
      v-if="hasOneShowingChild(item.children,item) && (!onlyOneChild.children||onlyOneChild.noShowingChildren)&&!item.alwaysShow">
      <app-link v-if="onlyOneChild.meta" :to="resolvePath(onlyOneChild.path, onlyOneChild.query)">
        <el-menu-item :index="resolvePath(onlyOneChild.path)" class="custom-menu"
                      :class="{'submenu-title-noDropdown':!isNest,'selected-menu':checkSelected(item)}">

          <item :icon="onlyOneChild.meta.icon||(item.meta&&item.meta.icon)"/>
          <span style="margin-top: 0">{{ onlyOneChild.meta.title }}</span>
        </el-menu-item>
      </app-link>
    </template>

    <el-submenu v-else ref="subMenu" :index="resolvePath(item.path)" popper-append-to-body>
      <template slot="title">
        <item v-if="item.meta" :icon="item.meta && item.meta.icon" :title="item.meta.title"/>
        <span style="height: 20px;line-height: 20px">{{ item.meta.title }}</span>
      </template>
      <sidebar-item
        v-for="(child, index) in item.children"
        :key="child.path + index"
        :is-nest="true"
        :item="child"
        :base-path="resolvePath(child.path)"
        class="nest-menu"
      />
    </el-submenu>
  </div>
</template>

<script>
import path from 'path'
import {isExternal} from '@/utils/validate'
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'
import variables from "@/assets/styles/variables.scss";

export default {
  name: 'SidebarItem',
  components: {Item, AppLink},
  computed: {
    variables() {
      return variables;
    },
  },
  mixins: [FixiOSBug],
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    },
    basePath: {
      type: String,
      default: ''
    }
  },
  data() {
    this.onlyOneChild = null
    return {}
  },
  methods: {
    checkSelected(item) {
      if (item.path === this.$route.path) {
        return true;
      }
      let children = (item.children || []);
      for (let child of children) {
        if (child.path === this.$route.path) {
          return true
        }
      }
      return false
    },
    hasOneShowingChild(children = [], parent) {
      if (!children) {
        children = [];
      }
      const showingChildren = children.filter(item => {
        if (item.hidden) {
          return false
        } else {
          // Temp set(will be used if only has one showing child)
          this.onlyOneChild = item
          return true
        }
      })

      // When there is only one child router, the child router is displayed by default
      if (showingChildren.length === 1) {
        return true
      }

      // Show parent if there are no child router to display
      if (showingChildren.length === 0) {
        this.onlyOneChild = {...parent, path: '', noShowingChildren: true}
        return true
      }

      return false
    },
    resolvePath(routePath, routeQuery) {
      if (isExternal(routePath)) {
        return routePath
      }
      if (isExternal(this.basePath)) {
        return this.basePath
      }
      if (routeQuery) {
        let query = JSON.parse(routeQuery);
        return {path: path.resolve(this.basePath, routePath), query: query}
      }
      return path.resolve(this.basePath, routePath)
    }
  }
}
</script>
<style lang="scss" scoped>

::v-deep.el-submenu .el-submenu__title {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70px;
  padding: 0 !important;
  color: #1890ff !important;
  position: relative;

  .el-submenu__icon-arrow {
    display: block!important;
    position: absolute;
    top: 50%;
    right: 5px;
  }

  .svg-icon {
    flex-shrink: 0;
    display: flex;
    margin: 0 !important;
    box-sizing: border-box;
    font-size: 1.5em;
    width: auto;
  }

}

.custom-menu {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70px;
  color: #1890ff !important;


  .svg-icon {
    flex-shrink: 0;
    display: flex;
    margin: 0 !important;
    box-sizing: border-box;
    font-size: 1.5em;
    width: auto;
  }

  & > span {
    line-height: 30px;
  }
}
.nest-menu{
  .custom-menu {
    padding: 0;
    height: 40px;
    flex-direction: row;
    justify-content: flex-start;
    .svg-icon{
      width: 50px;
    }
  }
}
</style>
