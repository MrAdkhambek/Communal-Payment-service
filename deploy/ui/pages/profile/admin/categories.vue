<template>
  <v-container class="block">
    <h1 class="text-3xl font-bold">
      Categories
    </h1>
    <div>
      <v-list-item v-for="category in categories" :key="category.id">
        <v-list-item-content>
          <v-list-item-title>{{ category.name }}</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </div>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      categories: []
    };
  },

  methods: {
    async fetchCategories() {
      try {
        const data = this.$axios.$get(`/category/`);

        this.categories = data.data;
      } catch (err) {
        console.error(err);
      }
    }
  },

  mounted() {
    this.fetchCategories();
  }
};
</script>

<style></style>
