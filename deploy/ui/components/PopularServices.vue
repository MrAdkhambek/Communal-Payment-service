<template>
  <div>
    <div>
      <v-card flat>
        <div
          class="my-8"
          v-for="{ id, name, photo, items } in categories"
          :key="id"
        >
          <div class="mb-6">
            <img class="inline-block mr-2" :src="photo" width="40" alt="" />
            <h2 class="text-3xl inline-block font-bold">
              {{ name }}
            </h2>
          </div>
          <v-card tile>
            <div v-for="(item, i) in items.slice(0, 3)" :key="item.id">
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>
                    {{ item.name }}
                  </v-list-item-title>
                </v-list-item-content>
                <v-list-item-action>
                  <v-btn-group>
                    <v-btn
                      elevation="0"
                      v-if="name === 'UJKX'"
                      class="text-xs  bg-green-50 text-green-600 font-semibold "
                      :to="`/balance/${item.id}`"
                    >
                      Balansni tekshirish
                    </v-btn>
                    <v-btn
                      elevation="0"
                      class="text-xs bg-green-500 text-white font-semibold "
                      :to="`/payment/${item.id}`"
                    >
                      To'lash
                    </v-btn>
                  </v-btn-group>
                </v-list-item-action>
              </v-list-item>
              <v-divider v-if="i != items.length - 1"></v-divider>
            </div>
          </v-card>
        </div>
      </v-card>
    </div>
    <!-- <v-list-item-group v-for="category in categories" :key="category.id">
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="text-semibold">
              <img width="30" class="inline-block" :src="category.photo" />
              <span class="ml-2 font-bold">
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
                class="text-xs  bg-gray-900 text-white font-semibold "
                :to="`/balance/${category.id}`"
              >
                Balansni tekshirish
              </v-btn>
              <v-btn
                elevation="0"
                class="text-xs bg-green-500 text-white font-semibold "
                :to="`/payment/${category.id}`"
              >
                To'lash
              </v-btn>
            </v-btn-group>
          </v-list-item-action>
        </v-list-item>
        <v-divider></v-divider>
      </v-list-item-group> -->
    <!-- <v-list-item>
      <v-list-item-content>
        <v-list-item-title>
          <nuxt-link to="/categories/"
            >Barcha xizmatlar
            <v-icon>
              mdi-open-in-new
            </v-icon>
          </nuxt-link>
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item> -->
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
