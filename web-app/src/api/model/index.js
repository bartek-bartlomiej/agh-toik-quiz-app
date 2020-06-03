const Difficulty = new Map([...new Set(['easy', 'medium', 'hard']).entries()])

class Category {
  constructor (id, name) {
    this.id = id
    this.name = name
  }

  validate (categories) {
    let errors = [
      ...typeof this.id !== 'number' ? ['ID should be a number'] : [],
      ...typeof this.name !== 'string' ? ['Name should be a string'] : []
    ]
    if (errors.length > 0) {
      return errors
    }

    errors = [
      ...this.id !== 0 && !categories.map(category => category.id).includes(this.id) ? ['Category with this ID not found'] : []
    ]
    if (errors.length > 0) {
      return errors
    }

    errors = [
      ...this.name === '' ? ['Name cannot be empty'] : [],
      ...categories.map(category => category.name).includes(this.name) ? ['Category with this name already exists'] : []
    ]
    return errors
  }

  toData () {
    return {
      id: this.id,
      name: this.name
    }
  }
}

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

class CategoryIdDTO {
  constructor (id) {
    this.id = id
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
    return new CategoryIdDTO(id)
  }
}

export {
  Difficulty,
  Category,
  QuizQueryDTO,
  CategoryIdDTO
}
