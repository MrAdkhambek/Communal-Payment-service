<template>
  <div>
    Logout
  </div>
</template>

<script>
export default {
  middleware({ store, redirect }) {
    store.commit("auth/LOG_OUT");
    redirect("/");
  }
};
</script>

<style></style>
