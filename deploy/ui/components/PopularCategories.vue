<template>
  <div>
    <v-card class="mx-auto" max-width="100%" tile>
      <template v-if="loading">
        <v-skeleton-loader
          v-for="item in 6"
          :key="item"
          class="py-1"
          max-width="300"
          type="list-item-avatar"
        ></v-skeleton-loader>
      </template>
      <v-list-item-group
        v-else
        v-for="category in categories"
        :key="category.id"
      >
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-semibold">
              <img width="30" class="inline-block" :src="category.photo" />
              <span class="text-lg ml-2 font-semibold">
                {{ category.name }}
              </span>
              <span class="text-green-500 text-sm">
                •
              </span>
              <span class="text-gray-500">
                {{ category.items.length }}
              </span>
            </v-list-item-title>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn-group>
              <v-btn
                elevation="0"
                class="text-xs bg-indigo-50 text-indigo-600 font-semibold "
                :to="`/payment/${category.id}`"
              >
                Xizmatlarni ko'rish
              </v-btn>
            </v-btn-group>
          </v-list-item-action>
        </v-list-item>
        <v-divider></v-divider>
      </v-list-item-group>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>
            <nuxt-link to="/categories/" class="text-indigo-500"
              >Barcha sohalarni ko'rish
              <v-icon>
                mdi-open-in-new
              </v-icon>
            </nuxt-link>
          </v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-card>
    <!-- <v-row>
      <v-col></v-col>
    </v-row> -->
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      loading: false,
      categories: []
    };
  },
  methods: {
    ...mapActions({
      fetchCategories: "categories/fetchCategories"
    })
  },
  async mounted() {
    try {
      this.loading = true;
      const { data } = await this.fetchCategories();

      this.categories = data.data;
    } catch (err) {
      this.$toast.error("Sohalarni yuklashda xatolik ro'y berdi");
    } finally {
      this.loading = false;
    }
  }
};
</script>

<style></style>
