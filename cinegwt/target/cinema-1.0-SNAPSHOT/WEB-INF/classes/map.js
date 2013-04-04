function () {
    for (var i = 0; i < this.kinds.length; i++) {
        emit(this.kinds[i].label, 1);
    }
}