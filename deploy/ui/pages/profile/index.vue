<template>
  <div>
    <h2 class="text-2xl mb-8">
      Xush kelibsiz, <strong>{{ user.name }}</strong>
    </h2>
    <template v-if="isAdmin">
      <v-tabs v-model="activeTab">
        <v-tab>
          <span>
            Barcha Sohalar
          </span>
        </v-tab>
        <v-btn
          @click="categoryDialog = true"
          icon
          class="mt-2 bg-green-100 text-green-600  ml-2"
        >
          <v-icon>
            mdi-plus
          </v-icon>
        </v-btn>
        <!-- <v-tab>Xizmatlar</v-tab> -->
      </v-tabs>
      <v-tabs-items v-model="activeTab">
        <v-tab-item class="py-2">
          <template v-if="isCategoriesLoading">
            <v-skeleton-loader
              v-for="item in 6"
              :key="item"
              class="py-2"
              max-width="300"
              type="list-item-avatar"
            ></v-skeleton-loader>
          </template>
          <v-card v-else flat>
            <v-expansion-panels accordion>
              <v-expansion-panel
                v-for="{ id, name, photo, items } in categories"
                :key="id"
              >
                <v-expansion-panel-header>
                  <div class="flex justify-between">
                    <div>
                      <span class="mr-2">
                        <img
                          class="inline-block"
                          :src="photo"
                          width="30"
                          alt=""
                        />
                      </span>
                      <span>
                        {{ name }}
                      </span>
                    </div>
                    <div>
                      <v-btn
                        icon
                        :loading="addingId == id"
                        @click.stop="onAddCategoryService(id)"
                        color="green"
                      >
                        <v-icon>
                          mdi-plus
                        </v-icon>
                      </v-btn>
                      <v-btn
                        icon
                        disabled
                        :loading="deletingId == id"
                        @click="handleDeleteCategory(id)"
                        color="error"
                      >
                        <v-icon>
                          mdi-trash-can-outline
                        </v-icon>
                      </v-btn>
                    </div>
                  </div>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                  <div v-for="(item, i) in items" :key="item.id">
                    <v-list-item>
                      <v-list-item-title>
                        <!-- <span class="mr-2">
                        <img
                          class="inline-block"
                          :src="photo"
                          width="30"
                          alt=""
                        />
                      </span> -->
                        <span>
                          {{ item.name }}
                        </span>
                      </v-list-item-title>
                      <v-list-item-actions>
                        <v-btn
                          icon
                          :loading="deletingId == item.id"
                          @click="handleDeleteService(item.id, id)"
                          color="error"
                        >
                          <v-icon>
                            mdi-trash-can-outline
                          </v-icon>
                        </v-btn>
                      </v-list-item-actions>
                    </v-list-item>
                    <v-divider v-if="i != items.length - 1"></v-divider>
                  </div>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-card>
        </v-tab-item>
        <!-- <v-tab-item>
        <template v-if="isServicesLoading">
          <v-skeleton-loader
            v-for="item in 4"
            :key="item"
            class=""
            max-width="300"
            type="list-item-avatar"
          ></v-skeleton-loader>
        </template>
        <v-card v-else flat>
          <div v-for="{ id, name, photo } in services" :key="id">
            <v-list-item>
              <v-list-item-title>
                <span class="mr-2">
                  <img class="inline-block" :src="photo" width="30" alt="" />
                </span>
                <span>
                  {{ name }}
                </span>
              </v-list-item-title>
              <v-list-item-actions>
                <v-btn
                  icon
                  :loading="deletingId == id"
                  @click="handleDeleteService(id)"
                  color="error"
                >
                  <v-icon>
                    mdi-trash-can-outline
                  </v-icon>
                </v-btn>
              </v-list-item-actions>
            </v-list-item>
            <v-divider></v-divider>
          </div>
        </v-card>
      </v-tab-item> -->
      </v-tabs-items>
      <CategoryDialog
        :visible.sync="categoryDialog"
        @closed="categoryDialog = false"
        @created="onCategoryCreated"
      />

      <v-dialog v-model="servicesDialog" persistent max-width="600px">
        <!-- <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on">
          Xizmat qo'shish
        </v-btn>
      </template> -->

        <v-card class="p-4">
          <v-form>
            <h3 class="text-2xl font-semibold">
              <!-- {{ selectedCategory && categoryOptions[selectedCategory].label }}ga -->
              Xizmat qo'shish
            </h3>

            <v-container>
              <v-row>
                <v-col cols="12" md="12">
                  <v-select
                    v-model="selectedCategory"
                    :items="categoryOptions"
                    item-text="label"
                    label="Sohani tanlang"
                  ></v-select>
                  <v-text-field
                    v-model="service.name"
                    label="Xizmatning nomi"
                    required
                  ></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                  <span class="text-gray-500">To'lov summasi (so'm):</span>
                  {{ service.range[0].toLocaleString() }} dan -
                  {{ service.range[1].toLocaleString() }} gacha
                  <v-range-slider
                    v-model="service.range"
                    :max="1000000"
                    :step="1000"
                    :min="10000"
                    class="align-center"
                  >
                  </v-range-slider>
                </v-col>

                <v-col cols="12" md="12">
                  <v-text-field
                    label="Rasm URL-ni kiriting"
                    v-model="service.photo"
                    counter
                  ></v-text-field>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
          <v-card-actions>
            <v-row>
              <v-col>
                <v-btn
                  block
                  text
                  class="bg-gray-200 "
                  @click="servicesDialog = false"
                >
                  Yopish
                </v-btn>
              </v-col>
              <v-col>
                <v-btn
                  block
                  class="bg-green-500 text-white"
                  text
                  @click="handleCreateService"
                  :loading="loading"
                >
                  Qo'sish
                </v-btn>
              </v-col>
            </v-row>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";

const defaultServiceValues = () => ({
  name: "",
  photo: "",
  range: [0, 500000]
});

const DEFAULT_CATEGORY_VALUES = {
  name: "",
  photo: null
};

export default {
  data() {
    return {
      addingId: null,
      deletingId: null,
      categories: [],
      services: [],
      isCategoriesLoading: false,
      isServicesLoading: false,
      loading: false,
      activeTab: 0,
      categoryDialog: false,
      servicesDialog: false,
      category: DEFAULT_CATEGORY_VALUES,
      selectedCategory: null,
      service: defaultServiceValues()
    };
  },

  computed: {
    ...mapState({
      user: state => state.auth.userData
    }),
    ...mapGetters({
      isAdmin: "auth/isAdmin"
    }),

    categoryOptions() {
      return this.categories.map(el => ({
        value: el.id,
        label: el.name
      }));
    }
  },

  methods: {
    ...mapActions({
      createCategoryService: "categories/createCategoryService",
      deleteCategory: "categories/deleteCategory",
      fetchCategories: "categories/fetchCategories",
      fetchServices: "services/fetchServices",
      deleteService: "services/deleteService"
    }),

    async getAllCategories() {
      try {
        this.isCategoriesLoading = true;
        const { data } = await this.fetchCategories();

        this.categories = data.data;
      } catch (err) {
        this.$toast.error("Sohalarni yuklashda xatolik ro'y berdi");
      } finally {
        this.isCategoriesLoading = false;
      }
    },

    async getCategoryServices() {
      try {
        this.loading = true;
        const { data } = await this.fetchServices();

        this.categories = data.data;
      } catch (err) {
        this.$toast.error("Sohalarni yuklashda xatolik ro'y berdi");
      } finally {
        this.loading = false;
      }
    },

    async getAllServices() {
      try {
        this.isServicesLoading = true;
        const { data } = await this.fetchServices();

        this.services = data.data;
      } catch (err) {
        this.$toast.error("Xizmatlarni yuklashda xatolik ro'y berdi");
      } finally {
        this.isServicesLoading = false;
      }
    },

    async handleCreateService() {
      try {
        this.loading = true;
        const { data } = await this.createCategoryService({
          name: this.service.name,
          photo: this.service.photo,
          id: this.selectedCategory,
          min: this.service.range[0],
          max: this.service.range[1]
        });

        this.$toast.success("Xizmat qo'shildi");

        const categoryIndex = this.categories.findIndex(
          t => t.id === this.selectedCategory
        );

        // this.services.push(data.data);
        let newCategories = Array.from(this.categories);

        newCategories[categoryIndex] = data.data;

        this.categories = newCategories;

        this.servicesDialog = false;
        this.service = defaultServiceValues();
        this.selectedCategory = null;
      } catch (err) {
        console.error(err);
      } finally {
        this.loading = false;
      }
    },
    async handleDeleteCategory(id) {
      console.log(id);
      try {
        this.deletingId = id;

        const response = await this.deleteCategory({ id });

        this.$toast.success("Soha o'chirildi");
        this.categories = this.categories.filter(t => t.id !== id);
      } catch (err) {
        this.$toast.error("Sohani o'chirishda xatolik ro'y berdi");
      } finally {
        this.deletingId = null;
      }
    },
    async handleDeleteService(id, categoryId) {
      try {
        console.log(id);
        this.deletingId = id;

        const response = await this.deleteService({ id });

        this.$toast.success("Xizmat o'chirildi");
        const categoryIndex = this.categories.findIndex(
          t => t.id === categoryId
        );

        this.categories[categoryIndex].items = this.categories[
          categoryIndex
        ].items.filter(t => t.id !== id);
      } catch (err) {
        this.$toast.error("Xizmat o'chirishda xatolik ro'y berdi");
      } finally {
        this.deletingId = null;
      }
    },
    onAddCategoryService(categoryId) {
      this.servicesDialog = true;
      this.selectedCategory = categoryId;
    },
    onCategoryCreated(newItem) {
      this.category = DEFAULT_CATEGORY_VALUES;
      this.categories.push(newItem);
    }
  },
  mounted() {
    this.getAllCategories();
  },
  watch: {
    activeTab: {
      handler(value) {
        if (value === 1 && this.services.length === 0) {
          this.getAllServices();
        }

        if (value === 0 && this.categories.length === 0) {
          this.getAllCategories();
        }
      }
    }
  }
};
</script>

<style></style>
