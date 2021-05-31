<template>
  <div>
    <div>
      <h1 class="text-4xl font-bold mb-4">
        Onlayn to'lovlar endi oson!
      </h1>
      <div>
        <v-autocomplete
          placeholder="Xizmat nomini kiriting"
          auto-select-first
          clearable
          filled
          solo
        ></v-autocomplete>
        <!-- <v-btn class="text-white bg-green-500">
          Qidirish
        </v-btn> -->
      </div>
    </div>
    <div>
      <div class="flex mb-4">
        <span class="mr-4">
          <img width="40" height="40" src="/images/zap.png" alt="" />
        </span>
        <h3 class="text-3xl font-bold">
          Ommabop sohalar
        </h3>
      </div>
      <PopularCategories />
      <PopularServices />
    </div>
  </div>
</template>

<script>
export default {
  async mounted() {
    // const { data } = await this.$store.dispatch("categories/fetchCategories");
    // const { data: userData } = await this.$axios.get(`/user/getMe`);
    // console.log("Categories", data);
    // console.log("User data", userData);
  }
};
</script>

<style></style>
