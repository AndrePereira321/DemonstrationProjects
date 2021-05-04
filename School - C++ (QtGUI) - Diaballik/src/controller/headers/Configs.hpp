#ifndef _CONFIGS_H
#define _CONFIGS_H

#include <stddef.h>

namespace dblk
{

/**
 * @brief DEFAULT_MOVES
 *
 * Default move quantity for turn.
 */
constexpr unsigned DEFAULT_MOVES = 2;

/**
 * @brief SMALL_SIZE
 *
 * Small size of the board.
 */
constexpr size_t SMALL_SIZE = 5;

/**
 * @brief MEDIUM_SIZE
 *
 * Medium size of the board.
 */
constexpr size_t MEDIUM_SIZE = 7;

/**
 * @brief BIG_SIZE
 *
 * Big size of the board.
 */
constexpr size_t BIG_SIZE = 9;
}

#endif //_CONFIGS_H
