const Difficulty = new Map([...new Set(['easy', 'medium', 'hard']).entries()])

class QuizQueryDTO {
  constructor (categoryId, difficulty, quantity) {
    this.category = categoryId
    this.difficulty = difficulty
    this.quantity = quantity
  }

  isValid () {
    return typeof this.category === 'number' &&
      Difficulty.has(this.difficulty) &&
      (this.quantity > 0 && this.quantity <= 100)
  }

  toParameters () {
    return {
      category: this.category.toString(),
      difficulty: this.difficulty.toString(),
      quantity: this.quantity.toString()
    }
  }

  static parseParameters (params) {
    const category = Number(params.category)
    const difficulty = Difficulty.get(params.difficulty)
    const quantity = Number(params.quantity)
    return new QuizQueryDTO(category, difficulty, quantity)
  }
}

class CategoryDTO {
  constructor (categoryId) {
    this.id = categoryId
  }

  isValid (categories) {
    return typeof this.id === 'number' &&
      categories.map(category => category.id).includes(this.id)
  }

  toParameters () {
    return {
      id: this.id.toString()
    }
  }

  static parseParameters (params) {
    const id = Number(params.id)
    return new CategoryDTO(id)
  }
}

export {
  Difficulty,
  QuizQueryDTO,
  CategoryDTO
}
