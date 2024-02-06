<script setup>
import {ref} from 'vue';

const start = ref(0)
const pageSize = 10
let diggUsers = ref(null);
fetchNext(start.value);

function fetchNext(startIndex) {
  fetch('http://localhost:8080/digg/user?' + new URLSearchParams({
    start: startIndex,
    nrToReturn: pageSize
  }))
      .then(response => response.json())
      .then(data => diggUsers.value = data);
}

function increase() {
  if (diggUsers.value.length > 0) {
    start.value = start.value + pageSize;
  }
  return start.value;
}

function decrease() {
  if (start.value > pageSize) {
    start.value = start.value - pageSize;
  } else {
    start.value = 0;
  }
  return start.value;
}
</script>

<template>
  <div class="container">
    <h2>Digg Users</h2>
    <button @click="fetchNext(decrease())">Previous</button>
    <button @click="fetchNext(increase())">Next</button>
    <table class="table table-bordered" style="text-align: left">

      <thead>
      <tr>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Telephone</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in diggUsers" :key="item.email">
        <td>{{ item.name }}</td>
        <td>{{ item.address }}</td>
        <td>{{ item.email }}</td>
        <td>{{ item.telephone }}</td>
      </tr>
      </tbody>
    </table>


  </div>
</template>