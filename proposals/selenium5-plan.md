# Plan for Selenium 5

Selenium needs a major release to highlight new features, and ensure all bindings are being managed consistently.
As such, the plan is to do a minimal focused Selenium 5 release making sure everyone is on the same page. 

## In scope
These items must be done before release
- New high-level async/event APIs (consistent concept across bindings, idiomatic per language) in all bindings
- Selenium Manager released with stable API, independent of Selenium version
- Established cross-bindings guidelines for how Selenium API is implemented with BiDi

## Out of Scope
These items may be done after Selenium 5 is released
- Implementing all Selenium APIs with BiDi commands
- Deprecating CDP code
- Major breaking changes

## Principles

### Backwards compatibility
The goal is minimum annoyance for users to upgrade
- Users should not need to make significant changes to their test code
- Existing Selenium deprecation policy applies to Selenium 5: only remove code that was deprecated for > 2 previous minor versions.
- Orchestration changes and dependency changes that cannot be deprecated may be acceptable

### BiDi implementation
BiDi is not a new Selenium API, it provides better tools for implementing and extending the existing API
- No references to BiDi in the API (casting or namespacing)
- Users can access BiDi implementation code, but it is clearly marked as internal usage / likely to change
- Enabling BiDi cannot break current Selenium API (requires graceful fallback)  
- Maintain consistent approach across bindings

## Implementation
The specific details for how these features are implemented and are managed across bindings will be managed
by GitHub Issues. Any issues with disagreements or that needs additional discussion or approval will be labeled as `A-needs-decision`
and discussed at the next TLC meeting.
