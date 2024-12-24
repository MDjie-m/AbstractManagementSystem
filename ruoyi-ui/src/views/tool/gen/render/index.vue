<template>
    <div class="app-container">
        <el-card>
            <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                label-width="120px">

                <el-row :gutter="10">
                    <el-col :span="20">
                        <el-form-item v-for="(column, index) in table.query" :key="index" :label="column.columnComment"
                            :prop="column.columnName">
                            <el-select v-if="column.htmlType == 'select'" v-model="queryParams[column.javaField]"
                                :placeholder="column.placeholder" clearable>
                                <el-option v-for="dict in column.dicts" :key="dict.dictValue" :label="dict.dictLabel"
                                    :value="dict.dictValue" />
                            </el-select>
                            <el-date-picker v-else-if="column.htmlType == 'datetime'"
                                v-model="queryParams[column.javaField]" value-format="yyyy-MM-dd" type="daterange"
                                range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
                            <el-input v-else v-model="queryParams[column.javaField]" :placeholder="column.placeholder"
                                clearable @keyup.enter.native="handleQuery" />
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                    </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <el-row :gutter="10" class="mb8">
                <el-col :span="1.5">
                    <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                        v-hasPermi="['business:look:add']">新增
                    </el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
                        @click="handleDelete" v-hasPermi="['business:look:remove']">删除
                    </el-button>
                </el-col>
                <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" border :data="itemList" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center" />
                <div v-for="(column, index) in table.columns" :key="index">
                    <el-table-column v-if="pk != column.javaField" :label="column.columnComment" align="center"
                        :prop="column.javaField" />
                </div>
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                        <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                            v-hasPermi="['business:look:edit']">修改
                        </el-button>
                        <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                            v-hasPermi="['business:look:remove']">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize" @pagination="getList" />
            <!-- 添加或修改对话框 -->
            <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                    <el-form-item v-for="(column, index) in table.form" :key="index" :label="column.columnComment"
                        :prop="column.columnName">
                        <el-input v-if="column.htmlType == 'input'" v-model="form[column.javaField]"
                            :placeholder="column.placeholder" />
                        <el-input v-else-if="column.htmlType == 'textarea'" type="textarea"
                            v-model="form[column.javaField]" :placeholder="column.placeholder" />
                        <el-date-picker v-else-if="column.htmlType == 'datetime'" v-model="form[column.javaField]"
                            :placeholder="column.placeholder" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" />
                        <el-date-picker v-else-if="column.htmlType == 'date'" v-model="form[column.javaField]"
                            :placeholder="column.placeholder" value-format="yyyy-MM-dd" type="date" />
                        <el-time-picker v-else-if="column.htmlType == 'time'" v-model="form[column.javaField]"
                            :placeholder="column.placeholder" value-format="HH:mm:ss" type="time" />
                        <el-radio-group v-else-if="column.htmlType == 'radio'" v-model="form[column.javaField]">
                            <el-radio v-for="dict in column.dicts" :key="dict.dictValue"
                                :label="dict.dictLabel">{{ dict.dictLabel }}
                            </el-radio>
                        </el-radio-group>
                        <el-radio-group v-else-if="column.htmlType == 'checkbox'" v-model="form[column.javaField]">
                            <el-checkbox v-for="dict in column.dicts" :key="dict.dictValue"
                                :label="dict.dictLabel">{{ dict.dictLabel }}
                            </el-checkbox>
                        </el-radio-group>
                        <el-select v-else-if="column.htmlType == 'select'" v-model="form[column.javaField]"
                            :placeholder="column.placeholder" clearable>
                            <el-option v-for="dict in column.dicts" :key="dict.dictValue" :label="dict.dictLabel"
                                :value="dict.dictValue" />
                        </el-select>
                        <image-upload v-else-if="column.htmlType == 'imageUpload'" v-model="form[column.javaField]" />
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
            </el-dialog>
        </el-card>
    </div>
</template>

<style></style>

<script>
import { getColumns, getTableDataList, getTableDataById, addTableData, updateTableData, delTableData } from "@/api/custom/data";

export default {
    name: "Custom",
    data() {
        return {
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 类目信息表格数据
            itemList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
            },
            pk: null,
            initForm: {},
            table: {
                columns: [],
                list: [],
                form: [],
                query: []
            },
            tableId: 0
        };
    },
    created() {
        let url = window.location.pathname
        let id = url.substring(url.lastIndexOf("/") + 1)
        console.log(id)
        this.tableId = id
        this.getColumnList();
    },
    methods: {
        getColumnList() {
            let id = this.tableId
            getColumns(id).then(response => {
                let data = response.data
                data.forEach(element => {
                    if (element.isPk == '1') {
                        this.pk = element.javaField
                    }
                });
                this.table.columns = data.filter(d => d.isList == 1);
                let queryForm = data.filter(d => d.isQuery == 1);
                queryForm.forEach(element => {
                    element.placeholder = "请输入" + element.columnComment
                });
                this.table.query = queryForm
                this.table.list = data
                let formArr = data.filter(d => d.isInsert == 1)
                formArr.forEach(element => {
                    element.placeholder = "请输入" + element.columnComment
                    this.form[element.javaField] = null
                });
                this.table.form = formArr;
                this.getList()
            });
        },
        /** 查询类目信息列表 */
        getList() {
            this.loading = true;
            let id = this.tableId
            getTableDataList(id, this.queryParams).then(response => {
                this.itemList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            let form = {}
            for (const key in this.form) {
                form[key] = null
            }
            this.form = form;
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
            let pk = this.pk
            this.ids = selection.map(item => item[pk])
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 新增按钮操作 */
        handleAdd() {
            this.reset();
            this.open = true;
            this.title = "添加";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            let pk = this.pk
            const id = row[pk] || this.ids
            getTableDataById(this.tableId, id).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改";
            });
        },
        handleExport() {
            this.download('business/item/export', {
                ...this.queryParams
            }, `数据导出.xlsx`)
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    console.log(this.form)
                    if (this.form[this.pk] != undefined) {
                        updateTableData(this.tableId, this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addTableData(this.tableId, this.form).then(response => {
                            /** 缺少符号 */
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            let pk = this.pk
            const ids = row[pk] || this.ids;
            let _this = this
            this.$modal.confirm('是否确认删除？').then(function () {
                return delTableData(_this.tableId, ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => {
            });
        }
    }
};
</script>
