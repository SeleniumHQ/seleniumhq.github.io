---
title: Selenium Doesn't Need MCP
linkTitle: Selenium Doesn't Need MCP
date: 2025-12-19
tags: ["selenium", "mcp"]
categories: ["technical"]
author: Alex Rodionov ([@p0deje](https://twitter.com/p0deje))
description: >
   Technical explanation of why we won't create an official MCP Server
---

Lately, our issue trackers and community channels have been buzzing with one recurring question: _"When will Selenium release an official Model Context Protocol (MCP) server?"_

It is a fair question. The industry is rapidly standardizing on MCP to connect AI agents with external tools. Given that Selenium is the de facto standard for browser automation, it seems like a logical next step for us to provide a native interface for Claude, Cursor, and other agents to "drive" the web.

However, at this point, we have decided that baking MCP directly into the Selenium core is not the right path. This isn't because we are skeptical of AI - far from it. It is because the specific constraints of Large Language Models make a native, low-level driver implementation inefficient and potentially harmful to agent performance.

## Naive Implementation

At its heart, Selenium is a **browser automation tool**. Our job is to provide a robust, W3C-standard protocol to control a browser. MCP, on the other hand, is an **agentic interface**.

The most common request we see is for a simple MCP server that exposes our API - `find_element`, `click`, `get_page_source` - as tools. While this sounds useful, in practice, it leads to immediate failure modes regarding _context window management_. 

We can look at early adopters for evidence. The official GitHub MCP server provides a wide array of tools, which results in massive context consumption. Its initial implementation exposed 100+ tools that consumed 64,000 tokens. That’s before the agent does any work. This issue was [improved](https://github.com/github/github-mcp-server/discussions/1182) over time, but even today, the server still uses 30,000 tokens on load.

If we were to expose the full WebDriver API via MCP, we would flood the agent with dozens of low-level tool definitions. This triggers [Context Rot](https://research.trychroma.com/context-rot), where the model becomes distracted by the sheer volume of available tools, degrading its reasoning capabilities.

There is [work](https://github.com/modelcontextprotocol/modelcontextprotocol/discussions/1920) happening to address this design limitation of MCP, though it’s not yet clear when it is going to be concluded and what the final version will look like. Still, this won’t solve the fundamental problem of context window management.

## The "Context" Dilemma

The hardest problem in agentic browsing is not _how to click_, but _what to see_. An agent needs to perceive the state of the page to decide what to do next.

The Playwright ecosystem has already encountered this hurdle. The standard Playwright MCP server implementation often returns the browser's accessibility tree snapshot to the agent. While cleaner than raw HTML, this tree can still be massive for modern web applications. 

Community benchmarks show that a single page snapshot can consume 15,000 to 50,000+ tokens. If an agent browses just three pages, it has filled its memory with DOM structures, pushing out the user's original instructions. The Playwright team [acknowledges](https://github.com/microsoft/playwright-mcp/issues/889#issuecomment-3264149677) this limitation, though they don’t believe it is their project's problem to solve. Here is what happens when you try to load Amazon with Claude Code:

```
> start browser and go to amazon

⏺ playwright - Navigate to a URL (MCP)(url: "https://www.amazon.com")
  ⎿ Error: result (205,897 characters) exceeds maximum allowed tokens.
```

Some models support [1M](https://ai.google.dev/gemini-api/docs/long-context) and even [10M](https://ai.meta.com/blog/llama-4-multimodal-intelligence/) tokens, and they can take you further, but their ability to reason and follow instructions degrades sooner than the limit is reached. Even when the model stays on track, it becomes slower and more expensive - compute scales [quadratically] with the context window! Overall, you’re better off having [small sessions](https://ampcode.com/200k-tokens-is-plenty) with an agent.

To work around this limitation, you need a highly opinionated "smart" compression. Some sort of heuristics that strip out layout divs, summarize lists, and use algorithms like SimHash to reduce token count. This level of opinionated logic belongs in an application layer, not in the driver itself. Within the Playwright ecosystem, it has already led to the creation of [better-playwright-mcp](https://github.com/livoras/better-playwright-mcp) and [fast-playwright-mcp](https://github.com/tontoko/fast-playwright-mcp), both of which are focused on reducing the token usage.

## Catch-22

In the Selenium ecosystem, similar problems exist in community-led projects like [mcp-selenium](https://github.com/angiejones/mcp-selenium) and [selenium-mcp](https://github.com/pshivapr/selenium-mcp). They attempt to map WebDriver directly to MCP for autonomous agents and, unfortunately, lead to context rot in the end. The tools they expose require specific selectors to act, so the agent is forced to read the entire DOM to locate elements. This creates too much data for the agent’s context window to handle. It is only useful when the page structure is known in advance, making it ineffective for independent exploration. 

Think of it as the [Catch-22 problem](https://en.wikipedia.org/wiki/Catch-22_(logic)). To see the DOM, the agent must request the page source. Requesting the page source overflows the context and makes the agent forget its instructions and ignore chunks of context. The agent cannot proceed anymore.

Although there are ways to circumvent this problem by using subagents, Anthropic’s [context editing](https://platform.claude.com/docs/en/build-with-claude/context-editing), or [advanced tool use](https://www.anthropic.com/engineering/advanced-tool-use) features, the industry lacks clarity on how the context window limitations will be solved long-term.

## Code Execution vs. Tool Calling

Perhaps the most compelling argument against a Selenium MCP server is the shift in how leading AI labs are building agents. Anthropic, the creators of MCP, have noted that **tool calling** (where the LLM chats back and forth: _Call Click -> Wait -> Call Type_) is [slow and expensive](https://www.anthropic.com/engineering/code-execution-with-mcp). They suggest moving toward **code execution** instead. In this model, the agent writes a script to perform a batch of actions and executes it once.
- **Tool calling**: 10 network round-trips to the LLM.
- **Code execution**: the LLM writes a Python script using Selenium bindings.

Selenium _is_ the standard for code execution. We already provide the perfect interface for agents: our Python, Java, JavaScript, and other language bindings. Wrapping these in an MCP server effectively downgrades them into a slower, chatty protocol.

## The Ecosystem

This brings us to our **Unix philosophy**: _do one thing and do it well_. Selenium automates browsers. We provide the engine.

The features required for a good MCP agent - auto-healing, smart waits, state management, and DOM summarization - are "batteries included" features. Over the years, the community has created dozens of projects in the [ecosystem](https://www.selenium.dev/ecosystem/)<sup>1</sup> that provide a better end-user experience.

We believe these and new frameworks are the correct place for MCP servers to live. They can be opinionated about how to present the page to an agent, while we focus on ensuring the driver works flawlessly with every browser version. New projects like [Alumnium](https://alumnium.ai) and [Vibium](https://vibium.com) emerge, focusing on solving the agentic-interface problem and using WebDriver as it is designed to be - a browser automation tool.

## Conclusion

We are excited to see what the community builds with Selenium and AI. But we believe our contribution should be maintaining the stable, standard-compliant WebDriver protocol that underpins it all. This ensures we keep a focus on WebDriver BiDi as the next-gen browser automation standard that other projects can be based on. We’ll get back to this topic whenever the MCP limitations and context rot problems are in a better state.

<sup>1</sup> _If you are familiar with an open source project based on the WebDriver ecosystem not listed there, please add it here and send us a pull request._
