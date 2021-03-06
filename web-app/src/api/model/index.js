const Difficulty = new Map([...new Set(['easy', 'medium', 'hard']).entries()])

class Question {
  id
  body
  answers
  correctAnswer
  category
  difficulty

  constructor (id, body, answers, correctAnswer, categoryId, difficulty) {
    this.id = id
    this.body = body
    this.answers = [...answers]
    this.correctAnswer = correctAnswer
    this.category = {
      id: categoryId
    }
    this.difficulty = difficulty
  }

  validate (categories) {
    let errors = {
      ...typeof this.id !== 'number' ? { id: 'ID should be a number' } : {},
      ...typeof this.body !== 'string' ? { body: 'Question content should be a string' } : {},
      ...typeof this.answers !== 'object' ? { answers: 'Answers should be array of string' } : {},
      ...!(typeof this.correctAnswer === 'number' || typeof this.correctAnswer === 'undefined')
        ? { correctAnswer: 'Correct Answer ID should be a number' }
        : {},
      ...typeof this.category.id !== 'number' ? { categoryId: 'Category ID should be a number' } : {},
      ...typeof this.difficulty !== 'string' ? { difficulty: 'Difficulty should be a string' } : {}
    }
    if (errors.answers === undefined) {
      errors = {
        ...errors,
        ...this.answers.reduce(
          (accumulator, answer, index) => typeof answer !== 'string'
            ? { ...accumulator, [`answer-${index}`]: 'Answer should be a string' }
            : accumulator,
          {})
      }
    }
    if (Object.keys(errors).length > 0) {
      return errors
    }
    errors = [
      this.body === '' ? { body: 'Question content cannot be empty' } : {},
      this.answers.length === 0
        ? { answers: 'At last one answer should be provided' }
        : this.answers.map((answer, index) => answer === ''
          ? { [`answer-${index}`]: 'Answer cannot be empty' }
          : {}),
      typeof this.correctAnswer === 'undefined'
        ? { answers: 'Correct answer should be chosen' }
        : !(this.correctAnswer >= 0 && this.correctAnswer < this.answers.length)
          ? { answers: 'Correct answer should be from a range of answers' }
          : {},
      !categories.map(category => category.id).includes(this.category.id)
        ? { categoryId: 'Category with this ID not found' } : {},
      !Difficulty.has(this.difficulty)
        ? { difficulty: `Difficulty should be one of ${Array.from(Difficulty.keys()).join(', ')}` } : {}
    ].flat()
      .map(error => Object.entries(error))
      .filter(error => error.length !== 0)
      .reduce((accumulator, [[field, error]]) => ({
        ...accumulator,
        [field]: accumulator[field] !== undefined ? [...accumulator[field], error] : [error]
      }),
      {})

    return errors
  }

  toData () {
    return {
      id: this.id,
      body: this.body,
      answers: this.answers,
      correctAnswer: this.correctAnswer,
      category: this.category,
      difficulty: this.difficulty
    }
  }
}

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
  Question,
  Category,
  QuizQueryDTO,
  CategoryIdDTO
}
